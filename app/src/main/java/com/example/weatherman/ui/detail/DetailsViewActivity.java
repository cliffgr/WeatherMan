package com.example.weatherman.ui.detail;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.weatherman.data.model.weather.CityWeather;
import com.example.weatherman.data.model.weather.CurrentCondition;
import com.example.weatherman.data.model.weather.Weather;
import com.example.weatherman.databinding.ActivityDetailsViewBinding;
import com.example.weatherman.ui.base.ViewModelFactory;
import com.example.weatherman.ui.detail.adapter.DailyReportAdapter;
import com.example.weatherman.ui.hourlyreport.HourlyActivity;


import java.util.List;

public class DetailsViewActivity extends AppCompatActivity {
    private static final String CITY_NAME = "ctn";
    private String cityName;
    private ActivityDetailsViewBinding activityDetailsViewBinding;
    private DetailsViewModel detailsViewModel;
    private DailyReportAdapter dailyReportAdapter;


    public static Intent newIntent(Context context, String cityName) {
        Bundle bundle = new Bundle();
        bundle.putString(CITY_NAME, cityName);
        Intent intent = new Intent(context, DetailsViewActivity.class);
        intent.putExtra("bundle", bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModelFactory factory = new ViewModelFactory(getApplication());
        activityDetailsViewBinding = ActivityDetailsViewBinding.inflate(getLayoutInflater());
        detailsViewModel = new ViewModelProvider(this, factory).get(DetailsViewModel.class);

        setSupportActionBar(activityDetailsViewBinding.topAppBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle bundle = getIntent().getBundleExtra("bundle");
        if (bundle != null) {
            cityName = bundle.getString(CITY_NAME);
        }

        if (savedInstanceState != null) {
            cityName = savedInstanceState.getString(CITY_NAME);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        dailyReportAdapter = new DailyReportAdapter();


        activityDetailsViewBinding.myRecyclerView.setLayoutManager(layoutManager);
        activityDetailsViewBinding.myRecyclerView.setAdapter(dailyReportAdapter);

        dailyReportAdapter.setClickListener(date -> {
            if (TextUtils.isEmpty(cityName))
                return;

            startActivity(HourlyActivity.newIntent(this, cityName, date));
        });

        detailsViewModel.getProgressBarLiveData().observe(this, flag -> {
            activityDetailsViewBinding.progressBar.setVisibility(flag ? View.VISIBLE : View.GONE);
        });

        detailsViewModel.getRequestResponse().observe(this, this::loadData);

        detailsViewModel.requestForCity(cityName);

        setContentView(activityDetailsViewBinding.getRoot());
    }


    private void loadData(CityWeather data) {
        if (data == null)
            return;
        CurrentCondition currentCondition = data.getData().getCurrentCondition().get(0);
        if (currentCondition == null)
            return;

        activityDetailsViewBinding.currentCityName.setText(data.getData().getRequest().get(0).getQuery());
        activityDetailsViewBinding.currentWeatherDescription.setText(currentCondition.getWeatherDesc().get(0).getValue());
        Glide.with(this)
                .load(currentCondition.getWeatherIconUrl().get(0).getValue().replace("http://", "https://"))
                .into(activityDetailsViewBinding.currentWeather);
        String weatherTempAndFeel = currentCondition.getTempC() + "°C / " + currentCondition.getFeelsLikeC() + "°C";
        activityDetailsViewBinding.currentTemperature.setText(weatherTempAndFeel);

        List<Weather> weatherList = data.getData().getWeather();
        if (weatherList == null || weatherList.size() < 6)
            return;
        dailyReportAdapter.setData(weatherList.subList(1, 6));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(CITY_NAME, cityName);
        super.onSaveInstanceState(outState);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
