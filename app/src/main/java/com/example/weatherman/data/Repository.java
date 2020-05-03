package com.example.weatherman.data;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import androidx.preference.PreferenceManager;
import androidx.room.Room;

import com.example.weatherman.data.local.database.AppDatabase;
import com.example.weatherman.data.local.database.Cities;
import com.example.weatherman.data.model.weather.CityWeather;
import com.example.weatherman.data.model.support.Supported;
import com.example.weatherman.data.remote.ApiRequest;
import com.example.weatherman.data.remote.RetrofitService;
import com.example.weatherman.utils.Constants;

import java.util.List;
import java.util.concurrent.ExecutionException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Repository {

    private ApiRequest apiRequest;
    private AppDatabase db;
    private SharedPreferences mPrefs;

    public Repository(Application application) {
        //TODO Remove allow on main Thread
        db = Room.databaseBuilder(application, AppDatabase.class, "DBNAME1").allowMainThreadQueries().build();
        mPrefs = PreferenceManager.getDefaultSharedPreferences(application);
        apiRequest = RetrofitService.getRetrofitInstance().create(ApiRequest.class);

    }

    public Observable<Supported> isCitySupported(String key, String city) {
        return apiRequest.getCountry(key, city, "json");

    }

    public Observable<CityWeather> getCurrentWeather(String key, String cityName) {
        return apiRequest.weatherPerCity(key, cityName, "json", "no", "no", "yes", "iso8601", "6").subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<CityWeather> getWeatherDetails(String key, String cityName) {
        return apiRequest.weatherPerCity(key, cityName, "json", "no", "yes", "yes", "iso8601", "6");
    }

    public Observable<CityWeather> getHourlyReport(String key, String cityName, String date) {
        return apiRequest.weatherForSpecificDate(key, cityName, "json", "no", "yes", "yes", "iso8601", date, "1");
    }


    public void insertCity(String cityName) {
        AsyncTask.execute(() -> {
            db.citiesDao().insertAll(new Cities(cityName));
        });

    }

    public void removeCity(String cityName) {
        AsyncTask.execute(() -> {
            db.citiesDao().delete(new Cities(cityName));
        });
    }

    public List<Cities> getCities() throws ExecutionException, InterruptedException {
        return new GetCitiesAsyncTask().execute().get();
    }


    public boolean isFirstRun() {
        return mPrefs.getBoolean(Constants.FIRST_RUN, true);
    }

    public void setFirstRun() {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putBoolean(Constants.FIRST_RUN, false);
        editor.apply();
    }

    private class GetCitiesAsyncTask extends AsyncTask<Void, Void, List<Cities>> {
        @Override
        protected List<Cities> doInBackground(Void... voids) {
            return db.citiesDao().getCities();
        }
    }
}

