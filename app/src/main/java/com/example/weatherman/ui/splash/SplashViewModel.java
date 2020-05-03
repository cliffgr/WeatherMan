package com.example.weatherman.ui.splash;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherman.data.Repository;

public class SplashViewModel extends AndroidViewModel {

    private Repository repository;
    private MutableLiveData<Boolean> isFirstRun;


    public MutableLiveData<Boolean> getFirstRun() {
        return isFirstRun;
    }

    public SplashViewModel(@NonNull Application application) {
        super(application);
        isFirstRun = new MutableLiveData<>();
        repository = new Repository(application);
    }

    public void checkFirstRun(){
        isFirstRun.setValue(repository.isFirstRun());
    }

}