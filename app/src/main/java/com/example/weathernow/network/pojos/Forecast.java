package com.example.weathernow.network.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by vivek on 03/12/17.
 */

public class Forecast {

    @SerializedName("forecastday")
    @Expose
    private List<Forecastday> forecastday = null;

    public List<Forecastday> getForecastday() {
        return forecastday;
    }

    public void setForecastday(List<Forecastday> forecastday) {
        this.forecastday = forecastday;
    }


    @Override
    public String toString() {
        return "Forecast{" +
                "forecastday=" + forecastday +
                '}';
    }
}