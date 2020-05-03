package com.example.weatherman.ui.hourlyreport;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherman.data.Repository;
import com.example.weatherman.data.model.weather.CityWeather;
import com.example.weatherman.utils.Constants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class HourlyViewModel extends AndroidViewModel {

    private Repository repository;
    private MutableLiveData<CityWeather> requestResponse = new MutableLiveData<>();
    private final CompositeDisposable disposables = new CompositeDisposable();


    public MutableLiveData<CityWeather> getRequestResponse() {
        return requestResponse;
    }

    public HourlyViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }


    public void requestForDailyReport(String cityName, String date) {
        disposables.add(repository.getHourlyReport(Constants.KEY, cityName, date)
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
