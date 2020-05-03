package com.example.weatherman.ui.tutorial.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.weatherman.ui.tutorial.tabs.AddCityFragment;
import com.example.weatherman.ui.tutorial.tabs.SelectCityFragment;
import com.example.weatherman.ui.tutorial.tabs.WelcomeFragment;

public class ViewPagerFragmentAdapter extends FragmentStateAdapter {

    public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new WelcomeFragment();
            case 1:
                return new SelectCityFragment();
            case 2:
                return new AddCityFragment();
        }
        return new WelcomeFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
