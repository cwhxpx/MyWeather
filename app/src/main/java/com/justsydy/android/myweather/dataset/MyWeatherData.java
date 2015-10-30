package com.justsydy.android.myweather.dataset;

import android.content.Context;

import com.justsydy.android.myweather.R;
import com.justsydy.android.myweather.util.ConstantsCamp;

/**
 * Created by cwh on 10/29/15.
 */
public class MyWeatherData {
    //mContext used to access app's resouce
    private Context mContext;

    //Today's weather summary
    private String mWeatherSummary;
    //backgroud for current weather
    private int mBkResourceId;

    public MyWeatherData(Context context){
        mContext = context;
    }

    //get and set method for mWeatherSummary
    public String getWeatherSummary() {
        return mWeatherSummary;
    }

    public void setWeatherSummary(String mWeatherSummary) {
        this.mWeatherSummary = mWeatherSummary;

        //set mainUI's background according to summary
        //we set only for 'clear', 'sunny', 'cloud' to test the solution because of
        //the real summary description is very complex
        if (mWeatherSummary.contains(ConstantsCamp.WEATHERDATA_SUMMARY_CLEAR)){
            setBkResourceId(R.color.summary_clear);
        }else if (mWeatherSummary.contains(ConstantsCamp.WEATHERDATA_SUMMARY_SUNNY)){
            setBkResourceId(R.color.summary_sunny);
        }else if (mWeatherSummary.contains(ConstantsCamp.WEATHERDATA_SUMMARY_CLOUDY)){
            setBkResourceId(R.color.summary_sunny);
        }else {
            setBkResourceId(R.color.summary_default);
        }
    }

    //get and set method for mWeatherSummary
    public int getBkResourceId() {
        return mBkResourceId;
    }

    public void setBkResourceId(int mBkResourceId) {
        this.mBkResourceId = mBkResourceId;
    }
}
