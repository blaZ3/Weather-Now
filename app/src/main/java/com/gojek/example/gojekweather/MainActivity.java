package com.gojek.example.gojekweather;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.gojek.example.gojekweather.databinding.ActivityMainBinding;
import com.gojek.example.gojekweather.events.WeatherEvents;
import com.gojek.example.gojekweather.network.pojos.Forecast;
import com.gojek.example.gojekweather.utils.Logger;
import com.gojek.example.gojekweather.weather.WeatherActivityViewModel;
import com.gojek.example.gojekweather.weather.WeatherForecastAdapter;
import com.gojek.example.gojekweather.weather.WeatherScreen;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements WeatherScreen {

    private static final String TAG = MainActivity.class.getSimpleName();
    ActivityMainBinding dataBinding;

    WeatherActivityViewModel weatherActivityViewModel;

    Animation loadingAnimation;
    Animation slideUpAnimation;

    WeatherForecastAdapter weatherForecastAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        loadingAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        slideUpAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up);

        weatherActivityViewModel = new WeatherActivityViewModel(new Logger());

        dataBinding.recyclerFuture.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.layoutError.btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherActivityViewModel.loadWeatherForcast();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        weatherActivityViewModel.loadWeatherForcast();
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void showFutureForecast(Forecast forecast) {
        weatherForecastAdapter = new WeatherForecastAdapter(MainActivity.this,
                forecast.getForecastday());
        dataBinding.recyclerFuture.setAdapter(weatherForecastAdapter);
        dataBinding.recyclerFuture.startAnimation(slideUpAnimation);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWeatherEvents(WeatherEvents weatherEvents){
        Log.d(TAG, "onWeatherEvents() called with: weatherEvents = [" + weatherEvents + "]");
        if (weatherEvents!=null){
            switch (weatherEvents.getAction()){
                case REFRESH:
                    refresh();
                    break;
                case SHOW_FORECAST:
                    showFutureForecast(weatherEvents.getFutureForecast().getForecast());
                    refresh();
                    break;
            }
        }
    }


    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startLoadingAnimation() {
        dataBinding.layoutLoading.imgLoading.startAnimation(loadingAnimation);
    }

    @Override
    public void stoptLoadingAnimation() {
        loadingAnimation.cancel();
        loadingAnimation.reset();
    }

    @Override
    public void refresh() {
        if (weatherActivityViewModel!=null){
            Log.d(TAG, "refresh: "+weatherActivityViewModel.toString());
            dataBinding.setViewModel(weatherActivityViewModel);
            if (weatherActivityViewModel.isShowLoading()){
                startLoadingAnimation();
            }else {
                stoptLoadingAnimation();
            }
        }else {
            showToast("Some error occurred!");
        }
    }
}
