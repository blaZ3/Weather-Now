package com.gojek.example.gojekweather.weather;

import com.gojek.example.gojekweather.network.pojos.Forecast;

/**
 * Created by vivek on 03/12/17.
 */

public interface WeatherScreen {

    void showFutureForecast(Forecast forecast);
    void showToast(String msg);

    void startLoadingAnimation();
    void stoptLoadingAnimation();

    void refresh();

}
