package com.example.sample.model

data class NewsResponse(val articles: List<NewsArticle>)

data class NewsArticle(
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String
)