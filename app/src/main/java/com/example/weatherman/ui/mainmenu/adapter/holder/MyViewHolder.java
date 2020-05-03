package com.example.weatherman.ui.mainmenu.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weatherman.R;
import com.example.weatherman.data.model.weather.CityWeather;
import com.example.weatherman.databinding.ItemWeatherBinding;
import com.example.weatherman.ui.base.ClickListener;

import java.lang.ref.WeakReference;

public class MyViewHolder extends RecyclerView.ViewHolder {

    private ItemWeatherBinding itemWeatherBinding;
    private WeakReference<ClickListener> listenerWeakReference;

    public MyViewHolder(ItemWeatherBinding binding, ClickListener clickListener) {
        super(binding.getRoot());
        itemWeatherBinding = binding;
        listenerWeakReference = new WeakReference<>(clickListener);


    }

    public void onBind(CityWeather weatherCityWeather) {
        itemWeatherBinding.cityName.setText(weatherCityWeather.getData().getRequest().get(0).getQuery());
        itemWeatherBinding.cityWeather.setText(String.format(itemWeatherBinding.cityWeather.getResources().getString(R.string.temperature_celsius), weatherCityWeather.getData().getCurrentCondition().get(0).getTempC()));
        itemWeatherBinding.cityWeatherDescription.setText(weatherCityWeather.getData().getCurrentCondition().get(0).getWeatherDesc().get(0).getValue());
        Glide.with(itemWeatherBinding.getRoot())
                .load(weatherCityWeather.getData().getCurrentCondition().get(0).getWeatherIconUrl().get(0).getValue().replace("http://", "https://"))
                .into(itemWeatherBinding.cityWeatherImage);
        itemWeatherBinding.getRoot().setOnClickListener(view -> {
            listenerWeakReference.get().onclick(weatherCityWeather.getData().getRequest().get(0).getQuery());
        });


    }
}
