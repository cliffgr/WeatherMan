package com.example.weatherman.data.local.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Cities.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CitiesDao citiesDao();
}