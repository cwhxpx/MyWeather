package com.justsydy.android.myweather.core;

import android.app.Application;
import android.util.Log;

import com.justsydy.android.myweather.R;
import com.justsydy.android.myweather.dataset.MyWeatherData;
import com.justsydy.android.myweather.service.ServiceAlarmReceiver;


/**
 * Created by cwh on 10/29/15.
 */
public class MyWeatherApp extends Application{

    //global weather data set accessed by UI and service
    public static MyWeatherData weatherSummary;

    @Override
    public void onCreate(){
        super.onCreate();
        Log.i("MyWeatherApp", "--->onCreate()");

        //initializing weather summary data set
        weatherSummary = new MyWeatherData(this);
        weatherSummary.setWeatherSummary(getString(R.string.myweather_welcome));

        //set a alarm for the task of weatherdata service
        ServiceAlarmReceiver.weatherServiceStarting(this);
    }
}
