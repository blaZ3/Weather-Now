package com.gojek.example.gojekweather.weather;

import android.util.Log;

import com.gojek.example.gojekweather.AppConstants;
import com.gojek.example.gojekweather.network.NetworkClient;
import com.gojek.example.gojekweather.network.pojos.response.FutureForecast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vivek on 03/12/17.
 */

public class WeatherModel {
    private static final String TAG = WeatherModel.class.getSimpleName();

    public void getForecast(final WeatherActivityViewModel.WeatherActivityViewModelInterface callback){
        Call<FutureForecast> call = NetworkClient.getWeatherService()
                .getFutureForecast(AppConstants.API_KEY, AppConstants.CITY, AppConstants.DAYS);
        call.enqueue(new Callback<FutureForecast>() {
            @Override
            public void onResponse(Call<FutureForecast> call, Response<FutureForecast> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                if (response.isSuccessful()){
                    callback.gotData(response.body());
                }else {
                    callback.error();
                }
            }
            @Override
            public void onFailure(Call<FutureForecast> call, Throwable t) {
                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                callback.networkError();
            }
        });
    }



}
