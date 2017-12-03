package com.gojek.example.gojekweather.weather;

import android.util.Log;

import com.gojek.example.gojekweather.events.WeatherEvents;
import com.gojek.example.gojekweather.network.pojos.response.FutureForecast;
import com.gojek.example.gojekweather.network.pojos.response.TodayForecast;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by vivek on 03/12/17.
 */

public class WeatherActivityViewModel {
    private static final String TAG = WeatherActivityViewModel.class.getSimpleName();

    private boolean showLoading;
    private boolean showNetworkError;
    private String errorMsg;

    private TodayForecast todayForecast;
    private FutureForecast futureForecast;

    private WeatherModel weatherModel;

    public WeatherActivityViewModel() {
        Log.d(TAG, "WeatherActivityViewModel() called");
        showLoading = true;
        showNetworkError = false;
        errorMsg = "Something went wrong at our end";

        weatherModel = new WeatherModel();
    }


    public void loadWeatherForcast(){
        setShowLoading(true);
        setShowNetworkError(false);

        //make api call
        weatherModel.getForecast(new WeatherActivityViewModelInterface() {
            @Override
            public void gotData(FutureForecast futureForecast) {
                setShowLoading(false);
                setShowNetworkError(false);

                EventBus.getDefault().post(new WeatherEvents(WeatherEvents.Action.SHOW_FORECAST,
                        futureForecast));
            }

            @Override
            public void error() {
                setShowLoading(false);
                setShowNetworkError(true);
                errorMsg = "Something went wrong at our end";
                EventBus.getDefault().post(new WeatherEvents(WeatherEvents.Action.REFRESH));
            }

            @Override
            public void networkError() {
                setShowLoading(false);
                setShowNetworkError(true);
                errorMsg = "Network error! Please check your network";
                EventBus.getDefault().post(new WeatherEvents(WeatherEvents.Action.REFRESH));
            }
        });

        EventBus.getDefault().post(new WeatherEvents(WeatherEvents.Action.REFRESH));
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

    public String getErrorMsg() {
        return errorMsg;
    }

    public TodayForecast getTodayForecast() {
        return todayForecast;
    }

    public FutureForecast getFutureForecast() {
        return futureForecast;
    }


    public interface WeatherActivityViewModelInterface{
        void gotData(FutureForecast futureForecast);
        void error();
        void networkError();
    }

    @Override
    public String toString() {
        return "WeatherActivityViewModel{" +
                "showLoading=" + showLoading +
                ", showNetworkError=" + showNetworkError +
                ", errorMsg='" + errorMsg + '\'' +
                ", todayForecast=" + todayForecast +
                ", futureForecast=" + futureForecast +
                ", weatherModel=" + weatherModel +
                '}';
    }
}
