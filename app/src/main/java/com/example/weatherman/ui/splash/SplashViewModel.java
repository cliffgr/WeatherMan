package com.example.weatherman.ui.splash;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherman.data.NetworkRepository;

public class SplashViewModel extends AndroidViewModel {

    private NetworkRepository networkRepository;
    private MutableLiveData<Boolean> isFirstRun;


    public MutableLiveData<Boolean> getFirstRun() {
        return isFirstRun;
    }

    public SplashViewModel(@NonNull Application application) {
        super(application);
        isFirstRun = new MutableLiveData<>();
        networkRepository = new NetworkRepository(application);
    }

    public void checkFirstRun(){
        isFirstRun.setValue(networkRepository.isFirstRun());
    }

}