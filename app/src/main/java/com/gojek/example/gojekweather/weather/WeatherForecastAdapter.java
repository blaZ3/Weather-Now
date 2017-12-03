package com.gojek.example.gojekweather.weather;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gojek.example.gojekweather.R;
import com.gojek.example.gojekweather.databinding.LayoutItemDayForecastBinding;
import com.gojek.example.gojekweather.network.pojos.Forecastday;
import com.gojek.example.gojekweather.utils.DataBindingViewHolder;

import java.util.List;

/**
 * Created by vivek on 03/12/17.
 */

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.WeatherForecastViewHolder> {


    public static class WeatherForecastViewHolder extends DataBindingViewHolder{
        public LayoutItemDayForecastBinding dataBinding;
        public WeatherForecastViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding);
            dataBinding = (LayoutItemDayForecastBinding) viewDataBinding;
        }
    }

    Context context;
    List<Forecastday> forecastdays;

    public WeatherForecastAdapter(Context context, List<Forecastday> forecastdays) {
        this.context = context;
        this.forecastdays = forecastdays;
    }

    @Override
    public WeatherForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        WeatherForecastViewHolder vh = new WeatherForecastViewHolder(DataBindingUtil
                .inflate(LayoutInflater.from(context), R.layout.layout_item_day_forecast,
                        parent, false));
        return vh;
    }

    @Override
    public void onBindViewHolder(WeatherForecastViewHolder holder, int position) {
        Forecastday forecastday = forecastdays.get(position);
        holder.dataBinding.setForecast(forecastday);
    }

    @Override
    public int getItemCount() {
        if (forecastdays!=null){
            return forecastdays.size();
        }
        return 0;
    }
}
