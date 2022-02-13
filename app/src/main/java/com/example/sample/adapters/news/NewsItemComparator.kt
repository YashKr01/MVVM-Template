package com.example.sample.adapters.news

import androidx.recyclerview.widget.DiffUtil
import com.example.sample.model.NewsArticle

class NewsItemComparator : DiffUtil.ItemCallback<NewsArticle>() {

    override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean =
        oldItem.url == newItem.url

    override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean =
        oldItem == newItem

}