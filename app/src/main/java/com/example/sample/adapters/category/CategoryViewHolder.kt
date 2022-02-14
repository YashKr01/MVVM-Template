package com.example.sample.adapters.category

import androidx.recyclerview.widget.RecyclerView
import com.example.sample.databinding.ItemCategoryBinding
import com.example.sample.model.CategoryEntity

class CategoryViewHolder (private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CategoryEntity) {
        binding.apply {
            chipText.text = item.title
        }
    }

}