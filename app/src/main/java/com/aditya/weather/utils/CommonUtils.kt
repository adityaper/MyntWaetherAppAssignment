package com.aditya.weather.utils

import android.content.Context
import android.location.LocationManager
import android.net.ConnectivityManager
import android.widget.Toast
import com.aditya.weather.R

object CommonUtils {


    const val SOMETHING_WENT_WRONG ="Something went wrong"
    const val ENTER_EMAIL ="Enter email"
    const val ENTER_PASSWORD ="Enter password"
    const val ENTER_NAME ="Enter name"
    const val REGISTRATION_SUCCESSFUL ="Registration Successful"
    const val LOGIN_SUCCESSFUL ="Login successful"
    const val USER_NOT_FOUND ="User not found"
    const val GPS_REQUEST_CODE = 99
    const val emailPattern ="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"


    fun showToast(context: Context, message:String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }

    fun isGpsEnable(context: Context):Boolean{
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val enableLocation = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (enableLocation){
            return true
        }else{
            Toast.makeText(context,R.string.please_turn_on_your_gps,Toast.LENGTH_LONG).show()
        }

        return false
    }

    fun checkConnection(context: Context): Boolean{
        if (isOnline(context)){
            return true
        }else{
            Toast.makeText(context,R.string.please_check_you_internet_connection,Toast.LENGTH_LONG).show()
        }
        return false
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo !=null && networkInfo.isConnectedOrConnecting
    }



}