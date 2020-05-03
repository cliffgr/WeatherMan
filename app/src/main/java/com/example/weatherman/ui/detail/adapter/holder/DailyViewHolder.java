package com.example.weatherman.ui.detail.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weatherman.R;
import com.example.weatherman.data.model.weather.CityWeather;
import com.example.weatherman.data.model.weather.Weather;
import com.example.weatherman.databinding.ItemDailyWeatherBinding;
import com.example.weatherman.databinding.ItemWeatherBinding;
import com.example.weatherman.ui.base.ClickListener;

import java.lang.ref.WeakReference;

public class DailyViewHolder extends RecyclerView.ViewHolder {

    private ItemDailyWeatherBinding itemDailyWeatherBinding;
    private WeakReference<ClickListener> listenerWeakReference;

    public DailyViewHolder(ItemDailyWeatherBinding binding, ClickListener clickListener) {
        super(binding.getRoot());
        itemDailyWeatherBinding = binding;
        listenerWeakReference = new WeakReference<>(clickListener);

    }

    public void onBind(Weather weatherCityWeather) {
        itemDailyWeatherBinding.min.setText(String.format(itemDailyWeatherBinding.min.getResources().getString(R.string.temperature_min_celsius), weatherCityWeather.getMintempC()));
        itemDailyWeatherBinding.max.setText(String.format(itemDailyWeatherBinding.max.getResources().getString(R.string.temperature_max_celsius), weatherCityWeather.getMaxtempC()));
        itemDailyWeatherBinding.av.setText(String.format(itemDailyWeatherBinding.av.getResources().getString(R.string.temperature_avg_celsius), weatherCityWeather.getAvgtempC()));
        itemDailyWeatherBinding.date.setText(weatherCityWeather.getDayOftheweek());
        itemDailyWeatherBinding.dateFull.setText(weatherCityWeather.getDate());
        itemDailyWeatherBinding.getRoot().setOnClickListener(view -> {
            listenerWeakReference.get().onclick(weatherCityWeather.getDate());
        });

    }
}
