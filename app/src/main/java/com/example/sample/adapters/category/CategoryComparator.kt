package com.example.sample.adapters.category

import androidx.recyclerview.widget.DiffUtil
import com.example.sample.model.CategoryEntity

class CategoryComparator : DiffUtil.ItemCallback<CategoryEntity>() {

    override fun areItemsTheSame(oldItem: CategoryEntity, newItem: CategoryEntity): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: CategoryEntity, newItem: CategoryEntity): Boolean =
        oldItem == newItem

}