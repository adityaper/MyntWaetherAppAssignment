package com.aditya.weather.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aditya.weather.getOrAwaitValue
import com.aditya.weather.model.WeatherResponse
import com.aditya.weather.repository.WeatherRepository
import com.aditya.weather.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class WeatherViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var weatherRepository: WeatherRepository

    lateinit var weatherViewModel: WeatherViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        weatherViewModel = WeatherViewModel(weatherRepository)
    }

    @Test
    fun getWeatherData() = runTest {
        val weatherResponse = WeatherResponse(name = "Aditya")
        Mockito.`when`(weatherRepository.getWeatherDataRemote(0.00, 0.00, "abc"))
            .thenReturn(weatherResponse)
        weatherViewModel.getWeatherDataRemote(0.00, 0.00, "abc")
        testDispatcher.scheduler.advanceUntilIdle()
        val result = weatherViewModel.weatherData.getOrAwaitValue()
        Assert.assertTrue(result is Resource.Success)
    }

    @Test
    fun getWeatherDataNullResponse() = runTest {
        val weatherResponse = null
        Mockito.`when`(weatherRepository.getWeatherDataRemote(0.00, 0.00, "abc"))
            .thenReturn(weatherResponse)
        weatherViewModel.getWeatherDataRemote(0.00, 0.00, "abc")
        testDispatcher.scheduler.advanceUntilIdle()
        val result = weatherViewModel.weatherData.getOrAwaitValue()
        Assert.assertTrue(result is Resource.Error)
    }

    @Test
    fun getWeatherHistory() = runTest {
        val weatherResponse = null
        Mockito.`when`(weatherRepository.getWeatherHistory()).thenReturn(weatherResponse)
        val result = weatherViewModel.getWeatherHistory()
        testDispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals(null, result)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}