package com.justsydy.android.myweather.util;

import java.util.Calendar;

/**
 * Created by cwh on 10/29/15.
 */
public class MyAlarmTimer {
    private int mHour;
    private int mMinute;
    private int mSecond;
    private int mMillisecond;

    public MyAlarmTimer(int hour, int minute, int second, int millisecond){
        mHour = hour;
        mMinute = minute;
        mSecond = second;
        mMillisecond = millisecond;
    }

    public static Calendar setMyAlarmClock(int hour, int minute, int second, int millisecond){
        Calendar alarmClock = Calendar.getInstance();
        alarmClock.set(Calendar.HOUR_OF_DAY, hour);
        alarmClock.set(Calendar.MINUTE, minute);
        alarmClock.set(Calendar.SECOND, second);
        alarmClock.set(Calendar.MILLISECOND, millisecond);

        //if time just set is ealier than current time in which situation
        // the time will go fire immediatelly, so we should add alarmClock time with 1 day
        if(alarmClock.getTimeInMillis() < System.currentTimeMillis()) {
            alarmClock.set(Calendar.DAY_OF_YEAR, alarmClock.get(Calendar.DAY_OF_YEAR) + 1);
        }

        return alarmClock;
    }
}
