package com.example.weathernow.events;


import com.example.weathernow.network.pojos.response.FutureForecast;

/**
 * Created by vivek on 03/12/17.
 */

public class WeatherEvents {

    public enum Action{
        REFRESH,
        SHOW_FORECAST
    }

    private FutureForecast futureForecast;

    private Action action;

    public WeatherEvents(Action action) {
        this.action = action;
    }

    public WeatherEvents(Action action, FutureForecast futureForecast) {
        this.action = action;
        this.futureForecast = futureForecast;
    }

    public Action getAction() {
        return action;
    }

    public FutureForecast getFutureForecast() {
        return futureForecast;
    }
}
