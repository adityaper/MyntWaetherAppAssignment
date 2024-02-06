package com.aditya.weather.utils

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UtilsTest {

    private lateinit var utils: Utils

    @Before
    fun setUp() {
        utils = Utils
    }

    @After
    fun tearDown() {
    }

    @Test
    fun convertKelvinToCel() {
        val result = utils.convertKelvinToCel(407.38)
        Assert.assertEquals("134.3° C", result)
    }

    @Test
    fun convertDate() {
        val result = utils.convertDate(1620399725)
        Assert.assertEquals("08:32 PM", result)
    }

    @Test
    fun getImageUrlByName() {
        val result = utils.getImageUrlByName("60d")
        Assert.assertEquals("https://openweathermap.org/img/wn/60d@4x.png", result)
    }

    @Test
    fun currentDateAndTime() {
        val result = utils.currentDateAndTime()
        Assert.assertTrue(result.isNotEmpty())
    }
}