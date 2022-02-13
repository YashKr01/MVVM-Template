package com.example.sample.adapters.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.sample.databinding.ItemTaskBinding
import com.example.sample.model.TaskEntity

class TaskAdapter(
    private val onUpdate: (TaskEntity) -> Unit,
    private val onDelete: (TaskEntity) -> Unit
) : ListAdapter<TaskEntity, TaskViewHolder>(TaskComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
        TaskViewHolder(
            ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onUpdate = { position ->
                val task = getItem(position)
                if (task != null) onUpdate(task)
            },
            onDelete = { position ->
                val task = getItem(position)
                if (task != null) onDelete(task)
            }
        )

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let { holder.bind(it) }
    }
}