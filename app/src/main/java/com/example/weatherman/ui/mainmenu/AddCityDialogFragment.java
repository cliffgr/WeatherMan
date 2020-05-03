package com.example.weatherman.ui.mainmenu;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.weatherman.R;
import com.example.weatherman.data.local.database.Cities;
import com.example.weatherman.databinding.FragmentAddCityBinding;
import com.example.weatherman.utils.Constants;
import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;

public class AddCityDialogFragment extends DialogFragment {

    private FragmentAddCityBinding fragmentAddCityBinding;
    private MainViewModel mainViewModel;
    private OnFragmentCloseListener onFragmentCloseListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

    }

    public void setOnHeadlineSelectedListener(OnFragmentCloseListener callback) {
        onFragmentCloseListener = callback;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentAddCityBinding = FragmentAddCityBinding.inflate(getLayoutInflater());
        fragmentAddCityBinding.toolbar.setNavigationOnClickListener(v -> dismiss());
        fragmentAddCityBinding.toolbar.setTitle("Manage your cities");

        return fragmentAddCityBinding.getRoot();
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // The only reason you might override this method when using onCreateView() is
        // to modify any dialog characteristics. For example, the dialog includes a
        // title by default, but your custom layout might not need it. So here you can
        // remove the dialog title, but you must call the superclass to get the Dialog.
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        fragmentAddCityBinding.editTextCityTextInputLayout.setStartIconOnClickListener(v -> {
            String input = fragmentAddCityBinding.editTextCity.getText().toString();
            if (input.length() > 0) {
                mainViewModel.requestForCity(input);
            }
        });

        mainViewModel.getCitiesMutableLiveData().observe(getViewLifecycleOwner(), data -> {
            fragmentAddCityBinding.chipGroup.removeAllViews();
            Log.e(Constants.TAG, "data size: " + data.size());
            for (Cities cities : data) {
                addChipToGroup(cities.getCityName());
            }
        });

        mainViewModel.getIsSupported().observe(getViewLifecycleOwner(), pair -> {
            if (pair.second) {
                mainViewModel.addCity(pair.first);
                addChipToGroup(pair.first);
            } else {
                showShackBar(pair.first);
            }
            fragmentAddCityBinding.editTextCity.setText("");
        });

        mainViewModel.getCities();
    }

    private void addChipToGroup(String chipName) {
        Chip chip = new Chip(requireActivity());
        chip.setText(chipName);
        chip.setTag(chipName);
        chip.setCloseIconVisible(true);
        chip.setOnCloseIconClickListener(v -> {
            mainViewModel.removeCity(chip.getText().toString());
            fragmentAddCityBinding.chipGroup.removeView(v);
        });
        fragmentAddCityBinding.chipGroup.addView(chip);
    }

    private void showShackBar(String msg) {
        Snackbar.make(fragmentAddCityBinding.container, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (onFragmentCloseListener != null)
            onFragmentCloseListener.onClose();
    }

    public interface OnFragmentCloseListener {
        void onClose();
    }

}