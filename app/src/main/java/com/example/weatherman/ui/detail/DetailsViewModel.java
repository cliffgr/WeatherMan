package com.example.weatherman.ui.detail;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherman.data.Repository;
import com.example.weatherman.data.model.weather.CityWeather;
import com.example.weatherman.ui.base.SingleLiveEvent;
import com.example.weatherman.utils.Constants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class DetailsViewModel extends AndroidViewModel {

    private Repository repository;
    private MutableLiveData<CityWeather> requestResponse;
    private SingleLiveEvent<Boolean> progressBarLiveData;
    private final CompositeDisposable disposables = new CompositeDisposable();


    public MutableLiveData<CityWeather> getRequestResponse() {
        return requestResponse;
    }

    public SingleLiveEvent<Boolean> getProgressBarLiveData() {
        return progressBarLiveData;
    }

    public DetailsViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        requestResponse = new MutableLiveData<>();
        progressBarLiveData = new SingleLiveEvent<>();
    }


    public void requestForCity(String cityName) {
        disposables.add(repository.getWeatherDetails(Constants.KEY, cityName)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    progressBarLiveData.postValue(true);
                })
                .doOnTerminate(() -> {
                    progressBarLiveData.postValue(false);
                })
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
