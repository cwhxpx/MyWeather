package com.justsydy.android.myweather.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.justsydy.android.myweather.util.ConstantsCamp;
import com.justsydy.android.myweather.util.MyAlarmTimer;

import java.util.Calendar;

/**
 * Created by cwh on 10/29/15.
 */
public class ServiceAlarmReceiver extends BroadcastReceiver{
    private static final String TAG = "ServiceAlarmReceiver";
    private static final int REQUEST_CODE = 1;

    @Override
    public void onReceive(Context context, Intent intent){

        //starting weather data picking service
        Intent i = PickupWeatherDataService.newIntent(context);
        context.startService(i);

        //setting next alarm for weather data service
        weatherServiceStarting(context);
    }

    //setting alarm for weather service task
    public static void weatherServiceStarting(Context context){
        //set a alarm timer at 12:00 every day
        Calendar alarmTimer = MyAlarmTimer.setMyAlarmClock(
                ConstantsCamp.serviceTimer[0],
                ConstantsCamp.serviceTimer[1],
                ConstantsCamp.serviceTimer[2],
                ConstantsCamp.serviceTimer[3]);

        //constructing intent for weather data service
        Intent intent = new Intent(context, ServiceAlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context,
                REQUEST_CODE, intent, PendingIntent.FLAG_CANCEL_CURRENT);


        //set alarm for weather data picking task
        AlarmManager alarmManager = (AlarmManager)
                context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,
                alarmTimer.getTimeInMillis(), pi);
    }
}
