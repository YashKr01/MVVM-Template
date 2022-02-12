package com.example.sample.adapters.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.sample.databinding.ItemBookBinding
import com.example.sample.model.BookEntity

class BookViewHolder(private val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item:BookEntity) {
        binding.apply {
            itemBookName.text = item.title

            Glide.with(itemView)
                .load(item.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.itemBookImage)

        }
    }

}