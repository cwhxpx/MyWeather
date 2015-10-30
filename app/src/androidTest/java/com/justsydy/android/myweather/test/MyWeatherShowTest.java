package com.justsydy.android.myweather.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.justsydy.android.myweather.R;
import com.justsydy.android.myweather.ui.MyWeatherShow;

/**
 * Created by cwh on 10/30/15.
 */
public class MyWeatherShowTest
        extends ActivityInstrumentationTestCase2<MyWeatherShow>{
    private MyWeatherShow mMyWeatherShow;
    private TextView mTestTextView;

    public MyWeatherShowTest(){
        super(MyWeatherShow.class);
    }

    @Override
    protected void setUp() throws Exception{
        super.setUp();
        mMyWeatherShow = getActivity();
        mTestTextView = (TextView)mMyWeatherShow.findViewById(R.id.myweather_data);
    }

    public void testPreconditions() {
        assertNotNull("mMyWeatherShow is null", mMyWeatherShow);
        assertNotNull("mTestTextView is null", mTestTextView);
    }

    public void testmWeatherDataShow_labelText() {
        final String expected =
                mMyWeatherShow.getString(R.string.myweather_welcome);
        final String actual = mTestTextView.getText().toString();
        assertEquals(expected, actual);
    }
}
