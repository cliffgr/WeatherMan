package com.example.weatherman.data.local.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Cities")
public class Cities {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "city")
    private String cityName;

    public Cities(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return this.cityName;
    }

    @Override
    public String toString() {
        return "Cities{" +
                "cityName='" + cityName + '\'' +
                '}';
    }
}
