package com.example.sample.adapters.news

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.sample.databinding.ItemNewsBinding
import com.example.sample.model.NewsArticle

class NewsViewHolder(
    private val binding: ItemNewsBinding,
    private val onReadMore: (Int) -> Unit,
    private val onShare: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.itemReadMore.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) onReadMore(adapterPosition)
        }
        binding.itemNewsShare.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) onShare(adapterPosition)
        }
    }

    fun bind(article: NewsArticle) {

        binding.apply {

            itemNewsDate.text = article.publishedAt.substring(0, 10)
            itemNewsDescription.text = article.description
            itemNewsTitle.text = article.title

            Glide.with(itemView)
                .load(article.urlToImage)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.itemNewsImage)

        }

    }

}