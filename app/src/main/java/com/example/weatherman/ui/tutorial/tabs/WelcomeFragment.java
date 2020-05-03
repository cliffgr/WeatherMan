package com.example.weatherman.ui.tutorial.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.weatherman.R;
import com.example.weatherman.databinding.SlideOneBinding;
import com.example.weatherman.databinding.SlideTwoBinding;

public class WelcomeFragment extends Fragment {
    private SlideOneBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = SlideOneBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}

