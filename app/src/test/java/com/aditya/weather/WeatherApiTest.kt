package com.aditya.weather

import com.aditya.weather.network.ApiService
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApiTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var apiService: ApiService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

    @Test
    fun testGetWeatherData_expected_empty_data() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setBody("{}")
        mockWebServer.enqueue(mockResponse)

        val response = apiService.getCurrentWeather(0.0, 0.0, "api_key")
        mockWebServer.takeRequest()

        Assert.assertEquals(null, response.body()?.weather)
    }

    @Test
    fun testGetWeatherData_response_error() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(404)
        mockResponse.setBody("Something went wrong")
        mockWebServer.enqueue(mockResponse)

        val response = apiService.getCurrentWeather(0.0, 0.0, "api_key")
        mockWebServer.takeRequest()

        Assert.assertEquals(404, response.code())
        Assert.assertEquals(false, response.isSuccessful)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}