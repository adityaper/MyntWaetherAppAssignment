package com.aditya.weather.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.aditya.weather.database.DatabaseClient
import com.aditya.weather.database.dao.WeatherDao
import com.aditya.weather.database.entities.Weather
import com.aditya.weather.model.WeatherResponse
import com.aditya.weather.network.ApiService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class WeatherRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockApiService: ApiService = mock()
    private val mockDatabaseClient: DatabaseClient = mock()

    private val weatherRepository = WeatherRepository(mockApiService, mockDatabaseClient)

    @Test
    fun getWeatherDataRemoteSuccess() = runTest {

        val mockWeatherResponse = WeatherResponse()

        val successResponse = Response.success(mockWeatherResponse)

        whenever(mockApiService.getCurrentWeather(0.0, 0.0, "API_KEY")).thenReturn(successResponse)

        val result = weatherRepository.getWeatherDataRemote(0.0, 0.0, "API_KEY")

        verify(mockApiService).getCurrentWeather(0.0, 0.0, "API_KEY")

        assertEquals(mockWeatherResponse, result)
    }

    @Test
    fun getWeatherDataRemoteFailure() = runTest {

        val failureResponse = Response.error<WeatherResponse>(404, mock())

        whenever(mockApiService.getCurrentWeather(0.0, 0.0, "API_KEY")).thenReturn(failureResponse)

        val result = weatherRepository.getWeatherDataRemote(0.0, 0.0, "API_KEY")

        verify(mockApiService).getCurrentWeather(0.0, 0.0, "API_KEY")

        assertEquals(null, result)
    }

    @Test
    fun addWeatherData() = runTest {

        val mockWeather = Weather(23.00, "https//demo.com", "amravati", "India", "12:12", 12, 6)

        val mockWeatherDao: WeatherDao = mock()
        whenever(mockDatabaseClient.getWeatherDao()).thenReturn(mockWeatherDao)

        whenever(mockWeatherDao.insertWeatherData(mockWeather)).thenReturn(Unit)

        weatherRepository.addWeatherData(mockWeather)

        verify(mockWeatherDao).insertWeatherData(mockWeather)
    }

    @Test
    fun getWeatherHistory() {

        val mockWeatherList =
            listOf(Weather(23.00, "https//demo.com", "amravati", "India", "12:12", 12, 6))

        val mockLiveData = MutableLiveData<List<Weather>>().apply { value = mockWeatherList }

        val mockWeatherDao: WeatherDao = mock()
        whenever(mockDatabaseClient.getWeatherDao()).thenReturn(mockWeatherDao)

        whenever(mockWeatherDao.getWeatherHistory()).thenReturn(mockLiveData)

        val result = weatherRepository.getWeatherHistory()

        verify(mockWeatherDao).getWeatherHistory()

        assertEquals(mockLiveData, result)
    }

}