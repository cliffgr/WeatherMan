package com.example.weatherman.ui.mainmenu;

import android.app.Application;
import android.util.Log;
import android.util.Pair;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.weatherman.data.local.database.Cities;
import com.example.weatherman.data.Repository;
import com.example.weatherman.data.model.common.Request;
import com.example.weatherman.data.model.support.Supported;
import com.example.weatherman.data.model.weather.CityWeather;
import com.example.weatherman.ui.base.SingleLiveEvent;
import com.example.weatherman.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class MainViewModel extends AndroidViewModel {
    private Repository repository;
    private MutableLiveData<List<CityWeather>> currentMutableLiveData;
    private MutableLiveData<List<Cities>> listCitiesMutableLiveData;
    private MutableLiveData<Supported> requestResponse = new MutableLiveData<>();
    private SingleLiveEvent<Boolean> progressBarLiveData;

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


    public MutableLiveData<List<CityWeather>> getCurrentMutableLiveData() {
        return currentMutableLiveData;
    }

    public MutableLiveData<List<Cities>> getCitiesMutableLiveData() {
        return listCitiesMutableLiveData;
    }

    public SingleLiveEvent<Boolean> getProgressBarLiveData() {
        return progressBarLiveData;
    }

    public MainViewModel(Application application) {
        super(application);
        repository = new Repository(application);
        currentMutableLiveData = new MutableLiveData<>();
        listCitiesMutableLiveData = new MutableLiveData<>();
        progressBarLiveData = new SingleLiveEvent<>();
    }


    public void fetchWeather() {

        /*for (Cities city : citiesList) {
            disposables.add((Disposable) networkRepository.getCurrentWeather(Constants.KEY, city.getCityName())
                    .subscribeOn(Schedulers.io())
            );
        }*/

        List<Observable<CityWeather>> observableList = new ArrayList<>();
        List<Cities> citiesList = getAllCities();
        if (citiesList.isEmpty()) {
            currentMutableLiveData.postValue(new ArrayList<>());
            return;
        }

        for (Cities city : citiesList) {
            final Observable<CityWeather> feedObservable =
                    repository.getCurrentWeather(Constants.KEY, city.getCityName())
                            .onErrorReturn(e -> new CityWeather());
            observableList.add(feedObservable);
        }

        Observable<List<CityWeather>> combinedObservable = Observable.zip(observableList, listOfLists -> {
            final List<CityWeather> combinedList = new ArrayList<>();
            for (Object current : listOfLists) {
                combinedList.add((CityWeather) current);
            }
            return combinedList;
        });

        combinedObservable
                .doOnSubscribe(disposable -> {
                    progressBarLiveData.setValue(true);
                })
                .doOnTerminate(() -> {
                    progressBarLiveData.setValue(false);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::result);

    }

    void requestForCity(String cityName) {
        disposables.add(repository.isCitySupported(Constants.KEY, cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> requestResponse.setValue(result)
                ));
    }

    void addCity(String cityName) {
        repository.insertCity(cityName);
    }

    void removeCity(String cityName) {
        repository.removeCity(cityName);
    }

    private void result(List<CityWeather> cityWeathers) {
        currentMutableLiveData.postValue(cityWeathers);
    }


    private List<Cities> getAllCities() {
        try {
            return repository.getCities();
        } catch (ExecutionException | InterruptedException e) {
            return new ArrayList<>();
        }
    }

    public void getCities() {
        listCitiesMutableLiveData.postValue(getAllCities());
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
