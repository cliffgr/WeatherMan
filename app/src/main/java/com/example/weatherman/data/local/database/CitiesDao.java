package com.example.weatherman.data.local.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CitiesDao {
    @Query("SELECT * FROM cities ORDER BY city ASC")
    List<Cities> getCities();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(Cities... cities);

    @Delete
    void delete(Cities cities);
}
