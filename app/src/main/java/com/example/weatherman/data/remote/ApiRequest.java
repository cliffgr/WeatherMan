package com.example.weatherman.data.remote;

import com.example.weatherman.data.model.weather.CityWeather;
import com.example.weatherman.data.model.support.Supported;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

public interface ApiRequest {

    @GET("weather.ashx")
    Observable<Supported> getCountry(@Query("key") String key, @Query("q") String city, @Query("format") String format);

    @GET("weather.ashx")
    Observable<CityWeather> weatherPerCity(@Query("key") String key, @Query("q") String city, @Query("format") String format, @Query("mca") String mca, @Query("fx") String fx, @Query("cc") String cc, @Query("date_format") String df, @Query("num_of_days") String num_of_days);

    @GET("weather.ashx")
    Observable<CityWeather> weatherForSpecificDate(@Query("key") String key, @Query("q") String city, @Query("format") String format, @Query("mca") String mca, @Query("fx") String fx, @Query("cc") String cc, @Query("date_format") String df, @Query("date") String date,@Query("tp") String tp);

}
