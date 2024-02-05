package com.aditya.weather.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weatherHistory")
data class Weather(
    var temp:Double?,
    var imgUrl:String?="",
    var city:String?="",
    var country:String?="",
    var time: String?="",
    var sunrise:Int?,
    var sunset:Int?
){
    @PrimaryKey(autoGenerate = true)
    var id:Long=0
}
