package com.example.weatherman.ui.detail;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherman.data.NetworkRepository;
import com.example.weatherman.data.model.weather.CityWeather;
import com.example.weatherman.utils.Constants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class DetailsViewModel extends AndroidViewModel {

    private NetworkRepository networkRepository;
    private MutableLiveData<CityWeather> requestResponse = new MutableLiveData<>();
    private final CompositeDisposable disposables = new CompositeDisposable();


    public MutableLiveData<CityWeather> getRequestResponse() {
        return requestResponse;
    }

    public DetailsViewModel(@NonNull Application application) {
        super(application);
        networkRepository = new NetworkRepository(application);
    }


    public void requestForCity(String cityName) {
        disposables.add(networkRepository.getWeatherDetails(Constants.KEY, cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> requestResponse.setValue(result)
                ));
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }


}
