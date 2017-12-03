package com.gojek.example.gojekweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gojek.example.gojekweather.weather.WeatherScreen;

public class MainActivity extends AppCompatActivity implements WeatherScreen {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



}
