package com.aditya.weather.repository


import com.aditya.weather.database.DatabaseClient
import com.aditya.weather.database.entities.Users
import javax.inject.Inject

class UserRepository @Inject constructor(private val databaseClient: DatabaseClient) {

    suspend fun insertUser(user: Users) = databaseClient.getUserDao().insertUser(user)

    suspend fun loginUser(email:String, password:String) = databaseClient.getUserDao().loginUser(email,password)

}