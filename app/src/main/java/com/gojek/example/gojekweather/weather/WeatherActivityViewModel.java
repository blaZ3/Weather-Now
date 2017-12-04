package com.gojek.example.gojekweather.weather;

import com.gojek.example.gojekweather.events.WeatherEvents;
import com.gojek.example.gojekweather.network.NetworkClient;
import com.gojek.example.gojekweather.network.pojos.response.FutureForecast;
import com.gojek.example.gojekweather.utils.ILogger;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by vivek on 03/12/17.
 */

public class WeatherActivityViewModel {
    private static final String TAG = WeatherActivityViewModel.class.getSimpleName();

    private boolean showLoading;
    private boolean showNetworkError;
    private String errorMsg;

    private FutureForecast futureForecast;

    private WeatherModel weatherModel;

    ILogger logger;

    public WeatherActivityViewModel(ILogger iLogger) {
        showLoading = true;
        showNetworkError = false;
        errorMsg = "Something went wrong at our end";

        logger = iLogger;

        weatherModel = new WeatherModel(iLogger, NetworkClient.getWeatherService());
    }

    public void loadWeatherForcast(){
        logger.d(TAG, "loadWeatherForcast() called");
        setShowLoading(true);
        setShowNetworkError(false);

        //make api call
        weatherModel.getForecast(new WeatherActivityViewModelInterface() {
            @Override
            public void gotData(FutureForecast futureForecast) {
                setShowLoading(false);
                setShowNetworkError(false);

                setFutureForecast(futureForecast);

                //raise the event to show the current and future forecasts
                EventBus.getDefault().post(new WeatherEvents(WeatherEvents.Action.SHOW_FORECAST,
                        futureForecast));
            }

            @Override
            public void error() {
                setShowLoading(false);
                setShowNetworkError(true);
                errorMsg = "Something went wrong at our end";

                //raise the event to refresh the UI
                EventBus.getDefault().post(new WeatherEvents(WeatherEvents.Action.REFRESH));
            }

            @Override
            public void networkError() {
                setShowLoading(false);
                setShowNetworkError(true);
                errorMsg = "Network error! Please check your network";

                //raise the event to refresh the UI
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

    public boolean isShowWeather() {
        return !showLoading && !showNetworkError;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setFutureForecast(FutureForecast futureForecast) {
        this.futureForecast = futureForecast;
    }

    public void setWeatherModel(WeatherModel weatherModel) {
        this.weatherModel = weatherModel;
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
                ", futureForecast=" + futureForecast +
                ", weatherModel=" + weatherModel +
                '}';
    }
}
