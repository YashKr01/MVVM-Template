package com.example.sample.adapters.recyclerviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.sample.adapters.comparators.WeatherItemComparator
import com.example.sample.adapters.viewholders.WeatherViewHolder
import com.example.sample.databinding.ItemWeatherBinding
import com.example.sample.model.WeatherItem

class WeatherAdapter : ListAdapter<WeatherItem, WeatherViewHolder>(WeatherItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder =
        WeatherViewHolder(
            ItemWeatherBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let { holder.bind(it) }
    }

}