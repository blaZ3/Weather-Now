package com.gojek.example.gojekweather;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.gojek.example.gojekweather.databinding.ActivityMainBinding;
import com.gojek.example.gojekweather.events.FutureForecastEvent;
import com.gojek.example.gojekweather.network.pojos.Forecast;
import com.gojek.example.gojekweather.network.pojos.Weather;
import com.gojek.example.gojekweather.weather.WeatherActivityViewModel;
import com.gojek.example.gojekweather.weather.WeatherScreen;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements WeatherScreen {

    ActivityMainBinding dataBinding;

    WeatherActivityViewModel weatherActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        weatherActivityViewModel = new WeatherActivityViewModel();
        refresh();

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFutureForecastEvent(FutureForecastEvent futureForecastEvent){
        weatherActivityViewModel.setShowLoading(false);

        if (futureForecastEvent.isSUCCESS() && futureForecastEvent.getFutureForecast()!=null){
            showTodaysWeather(futureForecastEvent.getFutureForecast().getCurrent());
            showFutureForecast(futureForecastEvent.getFutureForecast().getForecast());

            weatherActivityViewModel.setShowNetworkError(false);
        }else {
            weatherActivityViewModel.setShowNetworkError(true);
        }

        refresh();
    }

    @Override
    public void showTodaysWeather(Weather current) {

    }

    @Override
    public void showFutureForecast(Forecast forecast) {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refresh() {
        if (weatherActivityViewModel!=null){
            dataBinding.setData(weatherActivityViewModel);
        }else {
            showToast("Some error occurred!");
        }
    }
}
