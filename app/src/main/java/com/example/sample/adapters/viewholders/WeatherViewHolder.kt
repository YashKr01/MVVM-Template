package com.example.sample.adapters.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.sample.databinding.ItemWeatherBinding
import com.example.sample.model.WeatherItem

class WeatherViewHolder(private val binding: ItemWeatherBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: WeatherItem) {
        binding.apply {
            itemTitle.text = item.title
            itemValue.text = item.value
        }
    }

}