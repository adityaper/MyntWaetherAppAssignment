package com.aditya.weather.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aditya.weather.database.entities.Weather

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherData(weather: Weather)

    @Query("SELECT * FROM `weatherHistory` order by id desc")
    fun getWeatherHistory() : LiveData<List<Weather>>
}