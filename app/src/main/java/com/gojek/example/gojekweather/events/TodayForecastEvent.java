package com.gojek.example.gojekweather.events;

import com.gojek.example.gojekweather.network.pojos.response.TodayForecast;

/**
 * Created by vivek on 03/12/17.
 */

public class TodayForecastEvent {

    private boolean SUCCESS;
    private TodayForecast todayForecast;

    public TodayForecastEvent(boolean success, TodayForecast todayForecast) {
        this.SUCCESS = success;
        this.todayForecast = todayForecast;
    }

    public boolean isSUCCESS() {
        return SUCCESS;
    }

    public TodayForecast getTodayForecast() {
        return todayForecast;
    }
}
