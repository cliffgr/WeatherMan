package com.example.weatherman.ui.hourlyreport.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.weatherman.data.model.weather.Hourly;
import com.example.weatherman.ui.hourlyreport.HourlyFragment;

import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private List<Hourly> hourlyList;

    public ViewPagerAdapter(FragmentActivity fragment) {
        super(fragment);
    }

    public void setData(List<Hourly> hourlyList) {
        this.hourlyList = hourlyList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return  HourlyFragment.newInstance(hourlyList.get(position));
    }

    @Override
    public int getItemCount() {
        return 24;
    }
}
