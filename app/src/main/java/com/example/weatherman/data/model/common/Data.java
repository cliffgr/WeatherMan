package com.example.weatherman.data.model.common;

import com.example.weatherman.data.model.Error;
import com.example.weatherman.data.model.weather.CurrentCondition;
import com.example.weatherman.data.model.weather.Weather;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("error")
    @Expose
    private List<Error> error = null;
    @SerializedName("request")
    @Expose
    private List<Request> request = null;

    @SerializedName("current_condition")
    @Expose
    private List<CurrentCondition> currentCondition = null;

    @SerializedName("weather")
    @Expose
    private List<Weather> weather = null;



    public List<Error> getError() {
        return error;
    }

    public void setError(List<Error> error) {
        this.error = error;
    }

    public List<Request> getRequest() {
        return request;
    }

    public void setRequest(List<Request> request) {
        this.request = request;
    }

    public List<CurrentCondition> getCurrentCondition() {
        return currentCondition;
    }

    public void setCurrentCondition(List<CurrentCondition> currentCondition) {
        this.currentCondition = currentCondition;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "Data{" +
                "error=" + error +
                ", request=" + request +
                ", currentCondition=" + currentCondition +
                ", weather=" + weather +
                '}';
    }
}

