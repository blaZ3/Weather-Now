package com.gojek.example.gojekweather.weather;

import android.util.Log;

import com.gojek.example.gojekweather.network.pojos.response.FutureForecast;
import com.gojek.example.gojekweather.network.pojos.response.TodayForecast;

/**
 * Created by vivek on 03/12/17.
 */

public class WeatherActivityViewModel {
    private static final String TAG = WeatherActivityViewModel.class.getSimpleName();

    private boolean showLoading;
    private boolean showNetworkError;

    private TodayForecast todayForecast;
    private FutureForecast futureForecast;

    public WeatherActivityViewModel() {
        Log.d(TAG, "WeatherActivityViewModel() called");
        showLoading = true;
        showNetworkError = false;
    }


    public void loadWeatherForcast(){
        setShowLoading(true);
        setShowNetworkError(false);


    }


    public boolean isShowLoading() {
        return showLoading;
    }

    public boolean isShowNetworkError() {
        return showNetworkError;
    }

    public void setShowLoading(boolean showLoading) {
        this.showLoading = showLoading;
    }

    public void setShowNetworkError(boolean showNetworkError) {
        this.showNetworkError = showNetworkError;
    }

    public TodayForecast getTodayForecast() {
        return todayForecast;
    }

    public FutureForecast getFutureForecast() {
        return futureForecast;
    }
}
