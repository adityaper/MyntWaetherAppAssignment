package com.aditya.weather.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.aditya.weather.R
import com.aditya.weather.databinding.ActivityHomeBinding
import com.aditya.weather.ui.adapter.ViewPagerAdapter
import com.aditya.weather.ui.fragments.HistoryFragment
import com.aditya.weather.ui.fragments.TodayFragment
import com.aditya.weather.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initializing the ViewPagerAdapter
        val adapter = ViewPagerAdapter(supportFragmentManager)

        // add fragment to the list
        adapter.addFragment(TodayFragment(), getString(R.string.today))
        adapter.addFragment(HistoryFragment(), getString(R.string.history))

        // Adding the Adapter to the ViewPager
        binding.viewPager.adapter = adapter

        // bind the viewPager with the TabLayout.
        binding.tabs.setupWithViewPager(binding.viewPager)

    }
}