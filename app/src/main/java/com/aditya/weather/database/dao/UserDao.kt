package com.aditya.weather.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aditya.weather.database.entities.Users

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(users: Users): Long

    //checking user exist or not in our db
    @Query("SELECT * FROM Users WHERE email LIKE :email AND password LIKE :password")
    suspend fun loginUser(email: String, password: String): Users?

    @Query("DELETE FROM Users")
    fun deleteAll()

}