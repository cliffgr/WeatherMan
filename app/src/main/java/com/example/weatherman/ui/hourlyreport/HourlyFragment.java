package com.example.weatherman.ui.hourlyreport;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.weatherman.data.model.weather.Hourly;
import com.example.weatherman.databinding.FragmentHourlyBinding;


public class HourlyFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private Hourly hourly;
    private FragmentHourlyBinding fragmentHourlyBinding;


    public HourlyFragment() {
        // Required empty public constructor
    }

    public static HourlyFragment newInstance(Hourly hourly) {
        HourlyFragment fragment = new HourlyFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, hourly);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            hourly = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentHourlyBinding = FragmentHourlyBinding.inflate(getLayoutInflater());


        return fragmentHourlyBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        String weatherTempAndFeel = hourly.getTempC() + "°C / " + hourly.getFeelsLikeC() + "°C";
        fragmentHourlyBinding.temperature.setText(weatherTempAndFeel);
        fragmentHourlyBinding.weatherDescription.setText(hourly.getWeatherDesc().get(0).getValue());
        Glide.with(this)
                .load(hourly.getWeatherIconUrl().get(0).getValue().replace("http://", "https://"))
                .into(fragmentHourlyBinding.weatherIcon);

        fragmentHourlyBinding.windDescription.setText(hourly.getWindspeedKmph() + "km/h");
        fragmentHourlyBinding.humidityDescription.setText(hourly.getHumidity() + "%");
        fragmentHourlyBinding.gustDescription.setText(hourly.getWindGustKmph() + "km/h");
        fragmentHourlyBinding.rainDescription.setText(hourly.getChanceofrain() + "%");
    }
}
