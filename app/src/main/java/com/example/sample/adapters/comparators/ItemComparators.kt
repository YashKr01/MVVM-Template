package com.example.sample.adapters.comparators

import androidx.recyclerview.widget.DiffUtil
import com.example.sample.model.BookEntity
import com.example.sample.model.NewsArticle
import com.example.sample.model.TaskEntity
import com.example.sample.model.WeatherItem

class NewsItemComparator : DiffUtil.ItemCallback<NewsArticle>() {

    override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean =
        oldItem.url == newItem.url

    override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean =
        oldItem == newItem

}

class TaskComparator : DiffUtil.ItemCallback<TaskEntity>() {

    override fun areItemsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean =
        oldItem == newItem

}

class WeatherItemComparator : DiffUtil.ItemCallback<WeatherItem>() {

    override fun areItemsTheSame(oldItem: WeatherItem, newItem: WeatherItem): Boolean =
        oldItem.value == newItem.value

    override fun areContentsTheSame(oldItem: WeatherItem, newItem: WeatherItem): Boolean =
        oldItem == newItem

}

class BooksComparator : DiffUtil.ItemCallback<BookEntity>() {

    override fun areItemsTheSame(oldItem: BookEntity, newItem: BookEntity): Boolean =
        oldItem.image == newItem.image

    override fun areContentsTheSame(oldItem: BookEntity, newItem: BookEntity): Boolean =
        oldItem == newItem

}