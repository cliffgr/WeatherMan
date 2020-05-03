package com.example.weatherman.ui.tutorial.tabs;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.weatherman.R;
import com.example.weatherman.databinding.SlideThreeBinding;
import com.example.weatherman.ui.tutorial.TutorialViewModel;
import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;

public class AddCityFragment extends Fragment{
    private TutorialViewModel tutorialViewModel;
    private SlideThreeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = SlideThreeBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tutorialViewModel = new ViewModelProvider(requireActivity()).get(TutorialViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.editTextCityTextInputLayout.setStartIconOnClickListener(v->{
            String input = binding.editTextCity.getText().toString();
            if (input.length() > 0) {
                tutorialViewModel.requestForCity(input);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tutorialViewModel.getIsSupported().observe(getViewLifecycleOwner(), pair -> {
            if (pair.second) {
                tutorialViewModel.addCity(pair.first);
                Chip chip = new Chip(requireActivity());
                chip.setText(pair.first);
                chip.setTag(pair.first);
                chip.setCloseIconVisible(true);
                chip.setOnCloseIconClickListener(view -> {
                    tutorialViewModel.removeCity(chip.getText().toString());
                    binding.chipGroup.removeView(view);
                });
                binding.chipGroup.addView(chip);
            } else {
                showShackBar(pair.first);
            }
            binding.editTextCity.setText("");
        });
    }


    private void showShackBar(String msg) {
        Snackbar.make(binding.container, msg, Snackbar.LENGTH_SHORT).show();
    }
}
