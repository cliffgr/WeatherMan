package com.example.weatherman.ui.mainmenu;

import android.app.Application;
import android.util.Log;
import android.util.Pair;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.weatherman.data.local.database.Cities;
import com.example.weatherman.data.NetworkRepository;
import com.example.weatherman.data.model.common.Request;
import com.example.weatherman.data.model.support.Supported;
import com.example.weatherman.data.model.weather.CityWeather;
import com.example.weatherman.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class MainViewModel extends AndroidViewModel {
    private NetworkRepository networkRepository;
    private MutableLiveData<List<CityWeather>> currentMutableLiveData;
    private MutableLiveData<List<Cities>> listCitiesMutableLiveData;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private MutableLiveData<Supported> requestResponse = new MutableLiveData<>();
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

    public MainViewModel(Application application) {
        super(application);
        networkRepository = new NetworkRepository(application);
        currentMutableLiveData = new MutableLiveData<>();
        listCitiesMutableLiveData = new MutableLiveData<>();
    }


    public void fetchWeather() {

        /*for (Cities city : citiesList) {
            disposables.add((Disposable) networkRepository.getCurrentWeather(Constants.KEY, city.getCityName())
                    .subscribeOn(Schedulers.io())
            );
        }*/

        List<Observable<CityWeather>> observableList = new ArrayList<>();
        List<Cities> citiesList = getAllCities();

        Log.e(Constants.TAG, "Cities :" + citiesList.toString());

        if (citiesList == null || citiesList.isEmpty()) {
            currentMutableLiveData.postValue(new ArrayList<>());
            return;
        }

        for (Cities city : citiesList) {
            final Observable<CityWeather> feedObservable =
                    networkRepository.getCurrentWeather(Constants.KEY, city.getCityName())
                            .onErrorReturn(e -> new CityWeather());
            observableList.add(feedObservable);
        }

        Observable<List<CityWeather>> combinedObservable =
                Observable.combineLatest(observableList,
                        (listOfLists) -> {
                            final List<CityWeather> combinedList = new ArrayList<>();
                            for (Object current : listOfLists) {
                                combinedList.add((CityWeather) current);
                            }
                            return combinedList;
                        }
                );

        combinedObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::result);

    }

    public void requestForCity(String cityName) {
        disposables.add(networkRepository.isCitySupported(Constants.KEY, cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> requestResponse.setValue(result)
                ));
    }

    public void addCity(String cityName) {
        networkRepository.insertCity(cityName);
    }

    public void removeCity(String cityName) {
        networkRepository.removeCity(cityName);
    }

    private void result(List<CityWeather> cityWeathers) {
        currentMutableLiveData.postValue(cityWeathers);
    }


    private List<Cities> getAllCities() {
        try {
            return networkRepository.getCities();
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
