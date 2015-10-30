package com.justsydy.android.myweather.util;

/**
 * Created by cwh on 10/30/15.
 */
public class ConstantsCamp {
    public static final int SERVICE_PICK_INTERVAL = 1000 * 60 * 60 * 24; // 24 hours

    //shedule time for myweather pick up new weather data from backend service
    //11:45:00:000AM per day
    public static final int[] serviceTimer = {10, 0, 0, 0};

    //The url base for weather data service
    public static final String WEATHERDATA_RESTful_URL_BASE =
            "https://api.forecast.io/forecast/";
    public static final String WEATHERDATA_RESTful_URL_APIKEY =
            "8ca00276c7a2ed43d23b2d10cac32788";

    //below is a hardcode location at calstle hill to be used
    // while got location failure through GPS
    public static final String MAGIC_LOCATION = "-33.734533,151.002475";

    //weather data related terms
    public static final String RETURN_WEATHERDATA_JSONOBJECT_CURRENTLY = "currently";
    public static final String RETURN_WEATHERDATA_JSONOBJECT_SUMMARY = "summary";
    public static final String WEATHERDATA_SUMMARY_CLEAR = "clear";
    public static final String WEATHERDATA_SUMMARY_SUNNY = "sunny";
    public static final String WEATHERDATA_SUMMARY_CLOUDY = "cloudy";
}
