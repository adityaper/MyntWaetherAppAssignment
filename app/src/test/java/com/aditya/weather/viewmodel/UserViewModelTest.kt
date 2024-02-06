package com.aditya.weather.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aditya.weather.database.entities.Users
import com.aditya.weather.getOrAwaitValue
import com.aditya.weather.repository.UserRepository
import com.aditya.weather.utils.AppResponse
import com.nhaarman.mockitokotlin2.any
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserViewModelTest {

    /**'StandardTestDispatcher', which is used to set the main dispatcher for testing coroutines.*/
    private val testDispatcher = StandardTestDispatcher()

    /**This rule ensures that LiveData operations are executed synchronously on the test thread.*/
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    /**The repository variable is annotated with @Mock,
    indicating that it will be mocked using a mocking framework like Mockito.*/
    @Mock
    lateinit var repository: UserRepository

    lateinit var viewModel: UserViewModel

    /**The Mockito framework is initialized using MockitoAnnotations.openMocks(this).
    The main dispatcher is set to the testDispatcher dispatcher, and
    the viewModel is initialized with the mocked repository.*/
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = UserViewModel(repository)
    }

    @Test
    fun testValidInsertUser() = runTest {

        /**This line creates a new Users object with the specified values*/
        val user = Users(0, "aditya@gmail.com", "aditya", "aditya")

        /**This line sets up a mock behavior using Mockito.
         * It tells the mocked repository object that when the insertUser function is called with any parameter,
         * it should return 1. This is used for simulating the behavior of the repository during the test.*/
        Mockito.`when`(repository.insertUser(any())).thenReturn(1)

        /**This line invokes the insertUser function of the viewModel object,
         * passing the user object as a parameter. It triggers the operation being tested.*/
        viewModel.insertUser(user)

        /**This line advances the testDispatcher scheduler until all pending tasks are executed.
         * It ensures that any asynchronous operations triggered during the test,
         * such as coroutines or background threads, are completed before proceeding.*/
        testDispatcher.scheduler.advanceUntilIdle()

        /**This line retrieves the current value of appResponse from the viewModel object.
         * It seems that appResponse is a LiveData or similar observable object.
         * The getOrAwaitValue() function is likely a helper function to wait until the value is available.
         * The retrieved result will be used for assertion or further verification.*/
        val result = viewModel.appResponse.getOrAwaitValue()

        /**This line asserts that the result object is an instance of AppResponse.Success.
         * It verifies that the expected outcome of the operation is a successful response.*/
        assertTrue(result is AppResponse.Success)
    }

    @Test
    fun testInValidEmailInsertUser() = runTest {
        val user = Users(0, "adityagmailcom", "aditya", "aditya")
        Mockito.`when`(repository.insertUser(any())).thenReturn(1)
        viewModel.insertUser(user)
        testDispatcher.scheduler.advanceUntilIdle()
        val result = viewModel.appResponse.getOrAwaitValue()
        assertFalse(result is AppResponse.Success)
        assertTrue(result is AppResponse.Error)
    }

    @Test
    fun testEmptyEmailInsertUser() = runTest {
        val users = Users(0, "", "Aditya", "Aditya")
        Mockito.`when`(repository.insertUser(any())).thenReturn(1)
        viewModel.insertUser(users)
        testDispatcher.scheduler.advanceUntilIdle()
        val result = viewModel.appResponse.getOrAwaitValue()
        assertTrue(result is AppResponse.Error)
        assertFalse(result is AppResponse.Success)
    }

    @Test
    fun testEmptyPasswordInsertUser() = runTest {
        val users = Users(0, "aditya@gmail.com", "", "Aditya")
        Mockito.`when`(repository.insertUser(any())).thenReturn(1)
        viewModel.insertUser(users)
        testDispatcher.scheduler.advanceUntilIdle()
        val result = viewModel.appResponse.getOrAwaitValue()
        assertTrue(result is AppResponse.Error)
        assertFalse(result is AppResponse.Success)
    }

    @Test
    fun testEmptyNameInsertUser() = runTest {
        val users = Users(0, "aditya@gmail.com", "Aditya", "")
        Mockito.`when`(repository.insertUser(any())).thenReturn(1)
        viewModel.insertUser(users)
        testDispatcher.scheduler.advanceUntilIdle()
        val result = viewModel.appResponse.getOrAwaitValue()
        assertTrue(result is AppResponse.Error)
        assertFalse(result is AppResponse.Success)
    }

    @Test
    fun testSuccessLogin() = runTest {
        val users = Users(0, "aditya@mail.com", "Aditya", "Shya")
        Mockito.`when`(repository.loginUser("aditya@gmail.com", "Aditya")).thenReturn(users)
        viewModel.login("aditya@gmail.com", "Aditya")
        testDispatcher.scheduler.advanceUntilIdle()
        val result = viewModel.appResponse.getOrAwaitValue()
        assertTrue(result is AppResponse.Success)
        assertFalse(result is AppResponse.Error)
    }

    @Test
    fun testSuccessLoginNullResponse() = runTest {
        Mockito.`when`(repository.loginUser("aditya@gmail.com", "Aditya")).thenReturn(null)
        viewModel.login("aditya@gmail.com", "Aditya")
        testDispatcher.scheduler.advanceUntilIdle()
        val result = viewModel.appResponse.getOrAwaitValue()
        assertTrue(result is AppResponse.Error)
    }

    @Test
    fun testLoginInvalidEmail() = runTest {
        Mockito.`when`(repository.loginUser("adityagmailcom", "Aditya")).thenReturn(null)
        viewModel.login("adityagmailcom", "Aditya")
        testDispatcher.scheduler.advanceUntilIdle()
        val result = viewModel.appResponse.getOrAwaitValue()
        assertTrue(result is AppResponse.Error)
    }

    @Test
    fun testLoginInvalidPass() = runTest {
        Mockito.`when`(repository.loginUser("aditya@gmail.com", "")).thenReturn(null)
        viewModel.login("aditya@gmail.com", "")
        testDispatcher.scheduler.advanceUntilIdle()
        val result = viewModel.appResponse.getOrAwaitValue()
        assertTrue(result is AppResponse.Error)
    }

    @Test
    fun isValidEmail() {
        val result = viewModel.isValidEmail("adi@gmail.com")
        assertEquals(true, result)
    }

    @Test
    fun isInValidEmail() {
        val result = viewModel.isValidEmail("adi@gmailcom")
        assertEquals(false, result)
        val resultAd = viewModel.isValidEmail("adigmail.com")
        assertEquals(false, resultAd)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}