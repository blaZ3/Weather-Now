package com.gojek.example.gojekweather.weather;

import com.gojek.example.gojekweather.AppConstants;
import com.gojek.example.gojekweather.network.WeatherService;
import com.gojek.example.gojekweather.network.pojos.response.FutureForecast;
import com.gojek.example.gojekweather.utils.ILogger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vivek on 03/12/17.
 */

public class WeatherModel {
    private static final String TAG = WeatherModel.class.getSimpleName();

    ILogger logger;
    WeatherService weatherService;
    public WeatherModel(ILogger iLogger, WeatherService weatherService) {
        this.logger = iLogger;
        this.weatherService = weatherService;
    }

    public void getForecast(final WeatherActivityViewModel.WeatherActivityViewModelInterface callback){
        Call<FutureForecast> call = weatherService.getFutureForecast(
                AppConstants.API_KEY, AppConstants.CITY, AppConstants.DAYS);
        call.enqueue(new Callback<FutureForecast>() {
            @Override
            public void onResponse(Call<FutureForecast> call, Response<FutureForecast> response) {
                logger.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                if (response.isSuccessful() && response.errorBody() == null){
                    callback.gotData(response.body());
                }else {
                    callback.error();
                }
            }
            @Override
            public void onFailure(Call<FutureForecast> call, Throwable t) {
                logger.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                callback.networkError();
            }
        });
    }

    public void setWeatherService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
}
