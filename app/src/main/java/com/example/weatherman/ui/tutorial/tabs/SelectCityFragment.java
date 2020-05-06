package com.example.weatherman.ui.tutorial.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.weatherman.databinding.SlideTwoBinding;
import com.example.weatherman.ui.tutorial.TutorialViewModel;
import com.google.android.material.chip.Chip;

public class SelectCityFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    private TutorialViewModel tutorialViewModel;
    private SlideTwoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = SlideTwoBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tutorialViewModel = new ViewModelProvider(requireActivity()).get(TutorialViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        addChipToGroup("London");
        addChipToGroup("Athens");
        addChipToGroup("NewYork");
        addChipToGroup("Paris");
        addChipToGroup("Moscow");
        addChipToGroup("Tokyo");
    }

    private void addChipToGroup(String chipName) {
        Chip chip = new Chip(requireActivity());
        chip.setText(chipName);
        chip.setTag(chipName);
        chip.setCheckable(true);
        chip.setOnCheckedChangeListener(this);
        binding.chipGroup.addView(chip);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked)
            tutorialViewModel.addCity(buttonView.getText().toString());
        else
            tutorialViewModel.removeCity(buttonView.getText().toString());
    }
}
