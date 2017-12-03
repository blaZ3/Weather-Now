package com.gojek.example.gojekweather.events;

import com.gojek.example.gojekweather.network.pojos.response.FutureForecast;

/**
 * Created by vivek on 03/12/17.
 */

public class FutureForecastEvent {

    private boolean SUCCESS;
    private FutureForecast futureForecast;

    public FutureForecastEvent(boolean success, FutureForecast futureForecast) {
        this.SUCCESS = success;
        this.futureForecast = futureForecast;
    }

    public boolean isSUCCESS() {
        return SUCCESS;
    }

    public FutureForecast getFutureForecast() {
        return futureForecast;
    }
}
