package com.example.weatherman.ui.mainmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ViewSwitcher;

import com.example.weatherman.R;
import com.example.weatherman.databinding.ActivityMainBinding;
import com.example.weatherman.ui.base.ViewModelFactory;
import com.example.weatherman.ui.detail.DetailsViewActivity;
import com.example.weatherman.ui.mainmenu.adapter.WeatherAdapter;
import com.example.weatherman.ui.settings.SettingsActivity;
import com.example.weatherman.utils.Constants;

public class MainActivity extends AppCompatActivity implements AddCityDialogFragment.OnFragmentCloseListener {
    private boolean isLargeLayout;
    private MainViewModel viewModel;
    private ActivityMainBinding activityMainBinding;
    private WeatherAdapter weatherAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        ViewModelFactory factory = new ViewModelFactory(getApplication());
        viewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        isLargeLayout = getResources().getBoolean(R.bool.large_layout);

        weatherAdapter = new WeatherAdapter();

        activityMainBinding.myRecyclerView.setLayoutManager(layoutManager);
        activityMainBinding.myRecyclerView.setAdapter(weatherAdapter);

        activityMainBinding.fabIcon.setOnClickListener(v -> {
            showDialog();
        });

        weatherAdapter.setClickListener(cityName -> {
            startActivity(DetailsViewActivity.newIntent(MainActivity.this, cityName));
        });

        activityMainBinding.swipeRefresh.setOnRefreshListener(() -> {
            viewModel.fetchWeather();

        });

        viewModel.getCurrentMutableLiveData().observe(this, data -> {
            weatherAdapter.setData(data);

            if (activityMainBinding.swipeRefresh.isRefreshing()) {
                activityMainBinding.swipeRefresh.setRefreshing(false);
            }
        });

        activityMainBinding.topAppBar.setOnMenuItemClickListener(menuItem -> {
            if (menuItem.getItemId() == R.id.menu)
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        });


        viewModel.getProgressBarLiveData().observe(this, flag -> {
            activityMainBinding.progressBar.setVisibility(flag ? View.VISIBLE : View.GONE);
        });


        sharedPreferences.registerOnSharedPreferenceChangeListener((sharedPref, key) -> {
            if (key.equals("temp")) {
                Log.e(Constants.TAG, "Settings" + sharedPref.getString("temp", "0"));
            }

        });

        setContentView(activityMainBinding.getRoot());
    }

    public void showDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        AddCityDialogFragment newFragment = new AddCityDialogFragment();
        newFragment.show(fragmentManager, "dialog");

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.fetchWeather();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        if (fragment instanceof AddCityDialogFragment) {
            AddCityDialogFragment headlinesFragment = (AddCityDialogFragment) fragment;
            headlinesFragment.setOnHeadlineSelectedListener(this);
        }
    }

    @Override
    public void onClose() {
        viewModel.fetchWeather();
    }
}
