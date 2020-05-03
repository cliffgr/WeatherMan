package com.example.weatherman.ui.detail.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;


import com.example.weatherman.data.model.weather.Weather;
import com.example.weatherman.databinding.ItemDailyWeatherBinding;
import com.example.weatherman.databinding.ItemWeatherBinding;
import com.example.weatherman.ui.base.ClickListener;
import com.example.weatherman.ui.detail.adapter.holder.DailyViewHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DailyReportAdapter extends RecyclerView.Adapter<DailyViewHolder> {

    private List<Weather> weatherList;
    private ClickListener mclickListener;
    private int temp_type = 0;
    private SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private SimpleDateFormat spf2 = new SimpleDateFormat("EEEE", Locale.getDefault());

    public DailyReportAdapter() {
        weatherList = new ArrayList<>();
    }


    //Todo na ftiaxtei to update tis thermokrasias
    public void setTempType(int temp_type) {
        this.temp_type = temp_type;
        notifyDataSetChanged();
    }

    public void setClickListener(ClickListener clickListener) {
        this.mclickListener = clickListener;
    }


    public void setData(List<Weather> weatherList) {
        this.weatherList = weatherList;
        notifyDataSetChanged();
    }


    @Override
    public DailyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DailyViewHolder(ItemDailyWeatherBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false), mclickListener);
    }


    @Override
    public void onBindViewHolder(DailyViewHolder holder, int position) {
        Weather weatherCityWeather = weatherList.get(holder.getAdapterPosition());

        if (weatherCityWeather.getDayOftheweek()==null)
            try {
                Date date = spf.parse(weatherCityWeather.getDate());
                String changedDate = spf2.format(date);
                weatherCityWeather.setDayOftheweek(changedDate);
            } catch (ParseException | NullPointerException e) {

            }
        holder.onBind(weatherCityWeather);
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }
}
