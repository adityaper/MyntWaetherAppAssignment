package com.aditya.weather

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.aditya.weather.database.DatabaseClient
import com.aditya.weather.database.dao.WeatherDao
import com.aditya.weather.database.entities.Weather
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class TestWeatherDao {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var databaseClient: DatabaseClient
    lateinit var weatherDao: WeatherDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        databaseClient = Room.inMemoryDatabaseBuilder(
            context, DatabaseClient::class.java
        ).allowMainThreadQueries().build()
        weatherDao = databaseClient.getWeatherDao()
    }

    @Test
    fun insertData() = runBlocking {
        val weather = Weather(
            203.64,
            "45d",
            "Amravati",
            "In",
            "18-05-2023",
            1684495482,
            1686695482
        )
        weatherDao.insertWeatherData(weather)

        val result = weatherDao.getWeatherHistory().getOrAwaitValue()
        Assert.assertEquals(1, result.size)
        Assert.assertEquals("In", result[0].country)
        Assert.assertEquals("Amravati", result[0].city)
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        databaseClient.close()
    }

}