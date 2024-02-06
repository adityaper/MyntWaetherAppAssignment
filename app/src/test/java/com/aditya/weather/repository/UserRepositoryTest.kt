package com.aditya.weather.repository

import com.aditya.weather.database.DatabaseClient
import com.aditya.weather.database.dao.UserDao
import com.aditya.weather.database.entities.Users
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class UserRepositoryTest {

    @Mock
    private lateinit var databaseClient: DatabaseClient

    @Mock
    private lateinit var userDao: UserDao

    @InjectMocks
    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        `when`(databaseClient.getUserDao()).thenReturn(userDao)
    }

    @Test
    fun testInsertUser() = runTest {
        val user = Users(1, "adity@gmail.com", "password123", "Aditya")
        `when`(userDao.insertUser(user)).thenReturn(1)

        val result = userRepository.insertUser(user)

        assertEquals(1, result)
    }

    @Test
    fun testLoginUser() = runTest {
        val email = "adity@gmail.com"
        val password = "password123"
        val expectedUser = Users(1, "adity@gmail.com", "password123", "Aditya")
        `when`(userDao.loginUser(email, password)).thenReturn(expectedUser)

        val result = userRepository.loginUser(email, password)

        assertEquals(expectedUser, result)
    }
}