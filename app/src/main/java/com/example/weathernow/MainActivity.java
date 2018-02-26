package com.example.weathernow;

import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;


import com.example.weathernow.databinding.ActivityMainBinding;
import com.example.weathernow.events.WeatherEvents;
import com.example.weathernow.network.pojos.Forecast;
import com.example.weathernow.utils.Logger;
import com.example.weathernow.weather.WeatherActivityViewModel;
import com.example.weathernow.weather.WeatherForecastAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    ActivityMainBinding dataBinding;

    WeatherActivityViewModel weatherActivityViewModel;

    Animation loadingAnimation;
    Animation slideUpAnimation;
    Animation slideLeftAnimation;

    WeatherForecastAdapter weatherForecastAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        loadingAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        slideUpAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        slideLeftAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_left);

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

    public void showForecast(Forecast forecast) {
        weatherForecastAdapter = new WeatherForecastAdapter(MainActivity.this,
                forecast.getForecastday());
        dataBinding.recyclerFuture.setAdapter(weatherForecastAdapter);

        if (getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            dataBinding.recyclerFuture.startAnimation(slideUpAnimation);
        }else {
            dataBinding.recyclerFuture.startAnimation(slideLeftAnimation);
        }
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
                    showForecast(weatherEvents.getFutureForecast().getForecast());
                    refresh();
                    break;
            }
        }
    }


    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void startLoadingAnimation() {
        dataBinding.layoutLoading.imgLoading.startAnimation(loadingAnimation);
    }

    public void stopLoadingAnimation() {
        loadingAnimation.cancel();
        loadingAnimation.reset();
    }

    public void refresh() {
        if (weatherActivityViewModel!=null){
            Log.d(TAG, "refresh: "+weatherActivityViewModel.toString());
            dataBinding.setViewModel(weatherActivityViewModel);
            if (weatherActivityViewModel.isShowLoading()){
                startLoadingAnimation();
            }else {
                stopLoadingAnimation();
            }
        }else {
            showToast("Some error occurred!");
        }
    }
}
