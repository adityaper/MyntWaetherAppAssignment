package com.aditya.weather.repository

import androidx.lifecycle.LiveData
import com.aditya.weather.database.DatabaseClient
import com.aditya.weather.database.entities.Weather
import com.aditya.weather.model.WeatherResponse
import com.aditya.weather.network.ApiService
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiService: ApiService, private val db: DatabaseClient) {

    suspend fun getWeatherDataRemote(lat:Double,lon:Double, apiKey:String): WeatherResponse? {
        val response = apiService.getCurrentWeather(lat,lon,apiKey)
        return if (response.isSuccessful){
            response.body()
        }else{
            null
        }
    }

    suspend fun addWeatherData(weather: Weather) = db.getWeatherDao().insertWeatherData(weather)

    fun getWeatherHistory() : LiveData<List<Weather>> = db.getWeatherDao().getWeatherHistory()
}