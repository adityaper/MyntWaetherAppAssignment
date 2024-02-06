package com.aditya.weather

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.aditya.weather.database.DatabaseClient
import com.aditya.weather.database.dao.UserDao
import com.aditya.weather.database.entities.Users
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class TestUserDao {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var databaseClient: DatabaseClient
    lateinit var userDao: UserDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        databaseClient = Room.inMemoryDatabaseBuilder(
            context, DatabaseClient::class.java
        ).allowMainThreadQueries().build()
        userDao = databaseClient.getUserDao()
    }

    @Test
    fun testInsertData() = runBlocking {
        val user = Users(
            1,
            "aditya@gmail.com",
            "12Aditya@",
            "aditya"
        )
        userDao.insertUser(user)
        Assert.assertEquals(1, user.id)
        Assert.assertEquals("aditya", user.name)
        Assert.assertEquals("12Aditya@", user.password)
        Assert.assertEquals("aditya@gmail.com", user.email)
    }

    @Test
    fun testInvalidUser_expected_null() = runBlocking {
        val user = Users(
            1,
            "aditya@gmail.com",
            "12Aditya@",
            "aditya"
        )
        userDao.insertUser(user)
        val result = userDao.loginUser("aditya@gmail.com", "Aditya@")
        Assert.assertEquals(null, result)
    }

    @Test
    fun testLogin() = runBlocking {
        val user = Users(
            1,
            "aditya@gmail.com",
            "12Aditya@",
            "aditya"
        )
        userDao.insertUser(user)
        val result = userDao.loginUser("aditya@gmail.com", "12Aditya@")
        Assert.assertEquals("aditya@gmail.com", result?.email)
        Assert.assertEquals("12Aditya@", result?.password)
    }

    @Test
    fun testDeleteUser() = runBlocking {
        val user = Users(
            1,
            "aditya@gmail.com",
            "12Aditya@",
            "aditya"
        )
        userDao.insertUser(user)
        val result = userDao.loginUser("aditya@gmail.com", "12Aditya@")
        Assert.assertEquals("aditya@gmail.com", result?.email)
        userDao.deleteAll()
        val result1 = userDao.loginUser("aditya@gmail.com", "12Aditya@")
        Assert.assertEquals(null, result1)
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        databaseClient.close()
    }

}