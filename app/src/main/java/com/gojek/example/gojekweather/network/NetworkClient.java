package com.gojek.example.gojekweather.network;

import com.gojek.example.gojekweather.AppConstants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vivek on 03/12/17.
 */

public class NetworkClient {

    static WeatherService service;

    public static WeatherService getWeatherService(){
        if (service == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstants.ROOT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(WeatherService.class);
        }

        return service;
    }

}
