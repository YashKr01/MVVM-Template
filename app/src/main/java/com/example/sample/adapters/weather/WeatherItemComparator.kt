package com.example.sample.adapters.weather

import androidx.recyclerview.widget.DiffUtil
import com.example.sample.model.BookEntity
import com.example.sample.model.NewsArticle
import com.example.sample.model.TaskEntity
import com.example.sample.model.WeatherItem

class WeatherItemComparator : DiffUtil.ItemCallback<WeatherItem>() {

    override fun areItemsTheSame(oldItem: WeatherItem, newItem: WeatherItem): Boolean =
        oldItem.value == newItem.value

    override fun areContentsTheSame(oldItem: WeatherItem, newItem: WeatherItem): Boolean =
        oldItem == newItem

}