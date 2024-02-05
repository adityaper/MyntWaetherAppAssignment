package com.aditya.weather.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.weather.databinding.FragmentHistoryBinding
import com.aditya.weather.ui.activity.HomeActivity
import com.aditya.weather.ui.adapter.WeatherHistoryAdapter
import com.aditya.weather.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    lateinit var weatherViewModel: WeatherViewModel
    lateinit var weatherAdapter: WeatherHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherViewModel = (activity as HomeActivity).weatherViewModel

        setupRecyclerView()

        weatherViewModel.getWeatherHistory().observe( requireActivity(), Observer {
            weatherAdapter.differ.submitList(it.toList())
        })
    }

    private fun setupRecyclerView(){
        weatherAdapter = WeatherHistoryAdapter()
        binding.rvWeatherHistory.apply {
            adapter = weatherAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}