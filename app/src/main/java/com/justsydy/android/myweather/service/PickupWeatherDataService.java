package com.justsydy.android.myweather.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.util.Log;

import com.justsydy.android.myweather.R;
import com.justsydy.android.myweather.core.MyWeatherApp;
import com.justsydy.android.myweather.util.ConstantsCamp;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by cwh on 10/29/15.
 */
public class PickupWeatherDataService extends IntentService{

    private static final String TAG = "WeatherDataService";
    private String currentLocation;

    public static Intent newIntent(Context context){
        return new Intent(context, PickupWeatherDataService.class);
    }

    public PickupWeatherDataService(){
        super(TAG);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        currentLocation = getCurrentLocation(this);
        //Starting a async task to pick the weather data
        pickWeatherData(currentLocation);

        return START_STICKY;
    }

    @Override
    protected void onHandleIntent(Intent intent){
        Log.i(TAG, "Received an intent: " + intent);
    }

    //Task core for pick weather data from weather forcast backend
    private void pickWeatherData(String location){
        AsyncTask<String, Void, String> task = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                String result = null;
                RestTemplate template = new RestTemplate();
                template.getMessageConverters().add(new StringHttpMessageConverter());
                try {
                    result = template.getForObject(params[0], String.class);
                } catch (RestClientException e) {
                    result = "RestClientException";
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result) {

                if (null != result) {
                    if (!"RestClientException".equals(result)) {
                        try {
                            //Pick the weather summary from the json object returned
                            JSONObject jsonObject = new JSONObject(result).optJSONObject(
                                    ConstantsCamp.RETURN_WEATHERDATA_JSONOBJECT_CURRENTLY);

                            String weatherSummary = jsonObject.optString(
                                    ConstantsCamp.RETURN_WEATHERDATA_JSONOBJECT_SUMMARY);

                            //set the weather data into MyWeather data set
                            MyWeatherApp.weatherSummary.setWeatherSummary(weatherSummary);

                            //notify UI to update weather data through broadcasting
                            broadcastWeatherDataUpdate(PickupWeatherDataService.this);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        if (null != ConstantsCamp.WEATHERDATA_RESTful_URL_BASE) {
            String url = ConstantsCamp.WEATHERDATA_RESTful_URL_BASE
                       + ConstantsCamp.WEATHERDATA_RESTful_URL_APIKEY + "/"
                       + location;
            task.execute(url);
        }
    }

    private String getCurrentLocation(Context context){
        String currentLoc;
        LocationManager locMan = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
        Location location = locMan
                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location == null) {
            location = locMan
                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }

        if(/*location != null*/false){
            currentLoc = location.getLatitude() + "," + location.getLongitude();
        }else {
            currentLoc = ConstantsCamp.MAGIC_LOCATION;
        }
        return currentLoc;
    }

    //broadcast that weather data has been gotten from backend
    private void broadcastWeatherDataUpdate(Context context){
        Intent intent=new Intent();
        intent.setAction(context.getString(R.string.weather_service_broadcast));
        context.sendBroadcast(intent);
    }
}
