package com.example.sample.adapters.category

import androidx.recyclerview.widget.RecyclerView
import com.example.sample.databinding.ItemCategoryBinding
import com.example.sample.model.CategoryEntity

class CategoryViewHolder(
    private val binding: ItemCategoryBinding,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) onItemClick(adapterPosition)
        }
    }

    fun bind(item: CategoryEntity) {
        binding.apply {
            chipText.text = item.title
        }
    }

}