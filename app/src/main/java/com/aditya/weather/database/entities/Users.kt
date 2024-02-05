package com.aditya.weather.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class Users(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0,
    var email:String?="",
    var password: String?="",
    var name:String?=""

)