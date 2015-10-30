package com.justsydy.android.myweather.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.justsydy.android.myweather.R;
import com.justsydy.android.myweather.core.MyWeatherApp;

/**
 * Created by cwh on 10/29/15.
 */
public class MyWeatherShow extends Activity{
    private MyReceiver weatherDataUpdateReceiver = null;
    private TextView mWeatherDataShow;
    private RelativeLayout mMainUI;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_myweathershow);
        mMainUI = (RelativeLayout)findViewById(R.id.myweather_main_ui);
        mWeatherDataShow = (TextView) findViewById(R.id.myweather_data);

        //register a receiver to receive weather service broadcasting msg
        // on weather data update
        weatherDataUpdateReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(getString(R.string.weather_service_broadcast));
        registerReceiver(weatherDataUpdateReceiver, filter);
    }

    //A nested BroadcastReveiver which is used
    // receiving update msg from weatherdata service
    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //update weather data and refresh the background of the UI
            updateWeatherShow(context);
        }
    }

    //refresh UI with updated weather data
    public void updateWeatherShow(Context context){
        //update weather data and refresh the background of the UI
        mMainUI.setBackgroundResource(MyWeatherApp.weatherSummary.getBkResourceId());
        mWeatherDataShow.setText(MyWeatherApp.weatherSummary.getWeatherSummary());
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        //unregister broadcaster
        unregisterReceiver(weatherDataUpdateReceiver);
        weatherDataUpdateReceiver = null;
    }
}
