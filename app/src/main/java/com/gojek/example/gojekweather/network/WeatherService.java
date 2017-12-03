package com.gojek.example.gojekweather.network;

import com.gojek.example.gojekweather.network.pojos.response.FutureForecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by vivek on 03/12/17.
 */

public interface WeatherService {

    @GET("forecast.json")
    Call<FutureForecast> getFutureForecast(@Query("key") String key, @Query("q") String city,
                                           @Query("days") int days);

}
