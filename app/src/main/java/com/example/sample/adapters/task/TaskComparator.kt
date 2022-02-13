package com.example.sample.adapters.task

import androidx.recyclerview.widget.DiffUtil
import com.example.sample.model.TaskEntity

class TaskComparator : DiffUtil.ItemCallback<TaskEntity>() {

    override fun areItemsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean =
        oldItem == newItem

}