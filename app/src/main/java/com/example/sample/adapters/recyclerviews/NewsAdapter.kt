package com.example.sample.adapters.recyclerviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.sample.adapters.comparators.NewsItemComparator
import com.example.sample.adapters.viewholders.NewsViewHolder
import com.example.sample.databinding.ItemNewsBinding
import com.example.sample.model.NewsArticle

class NewsAdapter(
    private val onReadMore: (NewsArticle) -> Unit,
    private val onShare: (NewsArticle) -> Unit
) : ListAdapter<NewsArticle, NewsViewHolder>(NewsItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onReadMore = { position ->
                val article = getItem(position)
                if (article != null) onReadMore(article)
            },
            onShare = { position ->
                val article = getItem(position)
                if (article != null) onShare(article)
            }
        )

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let { holder.bind(it) }
    }

}