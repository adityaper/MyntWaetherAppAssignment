package com.aditya.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.weather.database.entities.Weather
import com.aditya.weather.model.WeatherResponse
import com.aditya.weather.repository.WeatherRepository
import com.aditya.weather.utils.CommonUtils
import com.aditya.weather.utils.Resource
import com.aditya.weather.utils.Utils.currentDateAndTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) : ViewModel(){

    private val _weatherData: MutableLiveData<Resource<WeatherResponse>> = MutableLiveData()
    val weatherData: LiveData<Resource<WeatherResponse>>
        get() = _weatherData

    fun getWeatherDataRemote(lat:Double, lon:Double, apiKey:String) = viewModelScope.launch {
        _weatherData.postValue(Resource.Loading())
        val response = weatherRepository.getWeatherDataRemote(lat,lon,apiKey)
        _weatherData.postValue(handleWeatherDataResponse(response)!!)
    }

    private fun handleWeatherDataResponse(response: WeatherResponse?): Resource<WeatherResponse>? {
        if (response != null){

                val weather = Weather(
                    temp = response.main?.temp,
                    imgUrl = response.weather?.get(0)?.icon,
                    city = response.name,
                    country = response.sys?.country,
                    time = currentDateAndTime(),
                    sunrise = response.sys?.sunrise,
                    sunset = response.sys?.sunset
                )
                viewModelScope.launch {
                    weatherRepository.addWeatherData(weather)
                }
                return Resource.Success(response)
        }
        return Resource.Error(CommonUtils.SOMETHING_WENT_WRONG)
    }

    fun getWeatherHistory() : LiveData<List<Weather>> {
        return weatherRepository.getWeatherHistory()
    }

}