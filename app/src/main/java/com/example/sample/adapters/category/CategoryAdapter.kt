package com.example.sample.adapters.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.sample.databinding.ItemCategoryBinding
import com.example.sample.model.CategoryEntity

class CategoryAdapter(private val onItemClick: (CategoryEntity) -> Unit) :
    ListAdapter<CategoryEntity, CategoryViewHolder>(CategoryComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClick = { position ->
                val article = getItem(position)
                if (article != null) onItemClick(article)
            }
        )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let { holder.bind(it) }
    }

}