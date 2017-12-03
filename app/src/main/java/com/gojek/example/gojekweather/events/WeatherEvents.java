package com.gojek.example.gojekweather.events;

/**
 * Created by vivek on 03/12/17.
 */

public class WeatherEvents {

    public enum Action{
        REFRESH,
        SHOW_FORECAST
    }

    private Action action;

    public WeatherEvents(Action action) {
        this.action = action;
    }

    public Action getAction() {
        return action;
    }
}
