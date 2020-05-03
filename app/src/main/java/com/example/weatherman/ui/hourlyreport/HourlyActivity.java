package com.example.weatherman.ui.hourlyreport;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.weatherman.databinding.ActivityHourlyBinding;
import com.example.weatherman.ui.hourlyreport.adapter.DemoCollectionAdapter;
import com.example.weatherman.utils.Constants;
import com.google.android.material.tabs.TabLayoutMediator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HourlyActivity extends AppCompatActivity {
    private static final String CITY_NAME = "ctn";
    private static final String DATE = "dt";
    private ActivityHourlyBinding hourlyBinding;
    private HourlyViewModel hourlyViewModel;
    private String cityName, date;
    private DemoCollectionAdapter demoCollectionAdapter;
    private SimpleDateFormat spf = new SimpleDateFormat("hhmm", Locale.getDefault());
    private SimpleDateFormat spf2 = new SimpleDateFormat("HH:mm", Locale.getDefault());


    public static Intent newIntent(Context context, String cityName, String date) {
        Bundle bundle = new Bundle();
        bundle.putString(CITY_NAME, cityName);
        bundle.putString(DATE, date);
        Intent intent = new Intent(context, HourlyActivity.class);
        intent.putExtra("bundle", bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hourlyBinding = ActivityHourlyBinding.inflate(getLayoutInflater());
        hourlyViewModel = new ViewModelProvider(this).get(HourlyViewModel.class);
        demoCollectionAdapter = new DemoCollectionAdapter(this);


        Bundle bundle = getIntent().getBundleExtra("bundle");
        if (bundle != null) {
            cityName = bundle.getString(CITY_NAME);
            date = bundle.getString(DATE);
        }


        hourlyViewModel.getRequestResponse().observe(this, data -> {
            demoCollectionAdapter.setData(data.getData().getWeather().get(0).getHourly());
            hourlyBinding.pager.setAdapter(demoCollectionAdapter);
            new TabLayoutMediator(hourlyBinding.tabLayout, hourlyBinding.pager, (tab, position) -> {
                String time = data.getData().getWeather().get(0).getHourly().get(position).getTime();
                tab.setText(time);
                /*  try {
                 *//*  Date date = null;
                    date = spf.parse(time);
                    String changedDate = spf2.format(date);
                    Log.e(Constants.TAG, "Time :" + time + " finalTime " + changedDate);*//*
                    tab.setText(time);
                } catch (ParseException e) {
                    tab.setText(time);
                }*/

            }).attach();
        });

        setContentView(hourlyBinding.getRoot());
    }



    @Override
    protected void onStart() {
        super.onStart();
        hourlyViewModel.requestForDailyReport(cityName, date);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
