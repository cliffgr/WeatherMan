package com.example.weatherman.ui.tutorial;

import android.app.Application;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.weatherman.data.Repository;

import com.example.weatherman.data.model.common.Request;
import com.example.weatherman.data.model.support.Supported;
import com.example.weatherman.utils.Constants;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class TutorialViewModel extends AndroidViewModel {

    private Repository repository;
    private MutableLiveData<Supported> requestResponse = new MutableLiveData<>();
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final LiveData<Pair<String, Boolean>> isSupported = Transformations.switchMap(requestResponse, response -> {
        MutableLiveData<Pair<String, Boolean>> flag = new MutableLiveData<>();
        if (response.getData().getError() != null)
            flag.setValue(new Pair<>(response.getData().getError().get(0).getMsg(), false));
        else {
            List<Request> request = response.getData().getRequest();
            for (Request rq : request) {
                String[] city = rq.getQuery().split(",");
                if (city.length > 0) {
                    flag.setValue(new Pair<>(city[0], true));
                }
            }
        }
        return flag;
    });


    public LiveData<Pair<String, Boolean>> getIsSupported() {
        return isSupported;
    }

    public TutorialViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public void addCity(String cityName) {
        repository.insertCity(cityName);
    }

    public void removeCity(String cityName) {
        repository.removeCity(cityName);
    }

    public void setFirstRun(){
        repository.setFirstRun();
    }

    public void requestForCity(String cityName) {
        disposables.add(repository.isCitySupported(Constants.KEY, cityName)
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
