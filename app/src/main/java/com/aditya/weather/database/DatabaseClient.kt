package com.aditya.weather.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aditya.weather.database.dao.UserDao
import com.aditya.weather.database.dao.WeatherDao
import com.aditya.weather.database.entities.Users
import com.aditya.weather.database.entities.Weather

@Database(entities = [Users::class, Weather::class], version = 1, exportSchema = false)
abstract class DatabaseClient:RoomDatabase() {
        abstract fun getUserDao(): UserDao
        abstract fun getWeatherDao(): WeatherDao
    }
