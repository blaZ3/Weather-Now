package com.gojek.example.gojekweather.network.pojos.response;


import com.gojek.example.gojekweather.network.pojos.Location;
import com.gojek.example.gojekweather.network.pojos.Weather;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vivek on 03/12/17.
 */

public class TodayForecast {

    @SerializedName("location")
    @Expose
    private Location location;

    @SerializedName("current")
    @Expose
    private Weather current;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Weather getCurrent() {
        return current;
    }

    public void setCurrent(Weather current) {
        this.current = current;
    }

    @Override
    public String toString() {
        return "TodayForecast{" +
                "location=" + location +
                ", current=" + current +
                '}';
    }
}