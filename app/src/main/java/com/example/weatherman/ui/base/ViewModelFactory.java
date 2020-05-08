package com.example.weatherman.ui.base;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.weatherman.ui.detail.DetailsViewModel;
import com.example.weatherman.ui.hourlyreport.HourlyViewModel;
import com.example.weatherman.ui.mainmenu.MainViewModel;
import com.example.weatherman.ui.splash.SplashViewModel;
import com.example.weatherman.ui.tutorial.TutorialActivity;
import com.example.weatherman.ui.tutorial.TutorialViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Application mApplication;

    public ViewModelFactory(Application application) {
        this.mApplication = application;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(mApplication);
        }else if(modelClass.isAssignableFrom(DetailsViewModel.class)){
            return (T) new DetailsViewModel(mApplication);
        }else if(modelClass.isAssignableFrom(HourlyViewModel.class)){
            return (T) new HourlyViewModel(mApplication);
        }else if(modelClass.isAssignableFrom(SplashViewModel.class)){
            return (T) new SplashViewModel(mApplication);
        }else if(modelClass.isAssignableFrom(TutorialViewModel.class)){
            return (T) new TutorialViewModel(mApplication);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }

}
