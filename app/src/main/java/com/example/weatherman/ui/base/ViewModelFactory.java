package com.example.weatherman.ui.base;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {



    public ViewModelFactory() {
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
       /* if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(mDataManager);
        }*/
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }

}
