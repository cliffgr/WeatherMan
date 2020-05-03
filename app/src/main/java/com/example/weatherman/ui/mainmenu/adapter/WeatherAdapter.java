package com.example.weatherman.ui.mainmenu.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherman.data.model.weather.CityWeather;
import com.example.weatherman.databinding.ItemWeatherBinding;
import com.example.weatherman.ui.base.ClickListener;
import com.example.weatherman.ui.mainmenu.adapter.holder.MyViewHolder;

import java.util.ArrayList;
import java.util.List;


public class WeatherAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<CityWeather> cityWeatherList;
    private ClickListener mclickListener;
    private int temp_type = 0;

    public WeatherAdapter() {
        cityWeatherList = new ArrayList<>();
    }


    //Todo na ftiaxtei to update tis thermokrasias
    public void setTempType(int temp_type) {
        this.temp_type = temp_type;
        notifyDataSetChanged();
    }

    public void setClickListener(ClickListener clickListener){
        this.mclickListener=clickListener;
    }


    public void setData(List<CityWeather> weatherCityWeatherList) {
        this.cityWeatherList = weatherCityWeatherList;
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(ItemWeatherBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false),mclickListener);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CityWeather weatherCityWeather = cityWeatherList.get(holder.getAdapterPosition());
        holder.onBind(weatherCityWeather);
    }

    @Override
    public int getItemCount() {
        return cityWeatherList.size();
    }
}
