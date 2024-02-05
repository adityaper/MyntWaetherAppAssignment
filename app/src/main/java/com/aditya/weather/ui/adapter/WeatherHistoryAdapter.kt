package com.aditya.weather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aditya.weather.database.entities.Weather
import com.aditya.weather.databinding.WeatherItemViewBinding
import com.aditya.weather.utils.Utils.convertDate
import com.aditya.weather.utils.Utils.convertKelvinToCel
import com.aditya.weather.utils.Utils.getImageUrlByName
import com.bumptech.glide.Glide

class WeatherHistoryAdapter: RecyclerView.Adapter<WeatherHistoryAdapter.WeatherViewHolder>() {

    inner class WeatherViewHolder(
        val itemViewBinding: WeatherItemViewBinding
    ): RecyclerView.ViewHolder(
        itemViewBinding.root
    )

    private val differCallBack = object : DiffUtil.ItemCallback<Weather>(){
        override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem.time == newItem.time
        }

        override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {

        return WeatherViewHolder(
            WeatherItemViewBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
       val weather = differ.currentList[position]


        val imgName = weather.imgUrl?.let { img ->
            getImageUrlByName(img)
        }?: run{
            getImageUrlByName("01d")
        }

        val temp = convertKelvinToCel(weather.temp)
        val time = weather.time
        val sunrise = convertDate(weather.sunrise)
        val sunset = convertDate(weather.sunset)

        holder.itemView.apply {
            Glide.with(this).load(imgName).into(holder.itemViewBinding.ivImgHistory)
        }
        holder.itemViewBinding.let {
            it.txtCity.text = "${weather.city}, "
            it.txtCountry.text = weather.country
            it.txtTemprature.text = temp
            it.txtTime.text = time
            it.txtSunriseTime.text = sunrise
            it.txtSunsetTime.text = sunset
        }
    }
}