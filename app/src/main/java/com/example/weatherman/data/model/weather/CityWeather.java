package com.example.weatherman.data.model.weather;

import com.example.weatherman.data.model.common.Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityWeather {

    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Example{" +
                "data=" + data +
                '}';
    }


}
