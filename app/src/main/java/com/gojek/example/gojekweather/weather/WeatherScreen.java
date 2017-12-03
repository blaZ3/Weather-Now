package com.gojek.example.gojekweather.weather;

import com.gojek.example.gojekweather.network.pojos.Forecast;
import com.gojek.example.gojekweather.network.pojos.Weather;

/**
 * Created by vivek on 03/12/17.
 */

public interface WeatherScreen {

    void showTodaysWeather(Weather current);
    void showFutureForecast(Forecast forecast);
    void showToast(String msg);

    void refresh();

}
