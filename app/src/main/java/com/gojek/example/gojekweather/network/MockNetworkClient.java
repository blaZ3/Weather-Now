package com.gojek.example.gojekweather.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vivek on 03/12/17.
 */

public class MockNetworkClient {

    static WeatherService service;

    public static WeatherService getWeatherService(String root){
        if (service == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(root)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(WeatherService.class);
        }

        return service;
    }

}
