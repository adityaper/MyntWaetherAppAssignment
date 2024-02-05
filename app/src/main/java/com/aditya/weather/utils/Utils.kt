package com.aditya.weather.utils

import com.aditya.weather.BuildConfig
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utils {

    fun convertKelvinToCel(temp: Double?): String {
        var intTemp = temp?:273.15
        intTemp = intTemp.minus(273.15)
        intTemp = intTemp.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()

        return "$intTemp° C"
    }

    fun convertDate(date: Int?): String {

        return SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date((date?:0) * 1000L))
    }

    fun getImageUrlByName(img: String):String{
        return "${BuildConfig.BASE_URL_ICON}${img}@4x.png"
    }


    fun currentDateAndTime(): String {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        return sdf.format(Date())
    }

}