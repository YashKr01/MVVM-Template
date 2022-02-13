package com.example.sample.adapters.book

import androidx.recyclerview.widget.DiffUtil
import com.example.sample.model.BookEntity

class BooksComparator : DiffUtil.ItemCallback<BookEntity>() {

    override fun areItemsTheSame(oldItem: BookEntity, newItem: BookEntity): Boolean =
        oldItem.image == newItem.image

    override fun areContentsTheSame(oldItem: BookEntity, newItem: BookEntity): Boolean =
        oldItem == newItem

}