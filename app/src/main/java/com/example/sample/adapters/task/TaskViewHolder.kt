package com.example.sample.adapters.task

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sample.R
import com.example.sample.databinding.ItemTaskBinding
import com.example.sample.model.TaskEntity
import com.example.sample.utils.Constants.PRIORITY_HIGH
import com.example.sample.utils.Constants.PRIORITY_LOW
import com.example.sample.utils.Constants.PRIORITY_MEDIUM

class TaskViewHolder(
    private val binding: ItemTaskBinding,
    private val onUpdate: (Int) -> Unit,
    private val onDelete: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.apply {
            itemTaskUpdate.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) onUpdate(adapterPosition)
            }
            itemTaskDelete.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) onDelete(adapterPosition)
            }
        }
    }

    fun bind(task: TaskEntity) {

        binding.apply {
            itemTaskTitle.text = task.title
            itemTaskDate.text = task.date
            when (task.priority) {
                PRIORITY_HIGH -> {
                    ImageViewCompat.setImageTintList(
                        itemTaskPriority,
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                root.context,
                                R.color.colorPriorityHigh
                            )
                        )
                    )
                }
                PRIORITY_MEDIUM -> {
                    ImageViewCompat.setImageTintList(
                        itemTaskPriority,
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                root.context,
                                R.color.colorPriorityMedium
                            )
                        )
                    )
                }
                else -> {
                    ImageViewCompat.setImageTintList(
                        itemTaskPriority,
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                root.context,
                                R.color.colorPriorityLow
                            )
                        )
                    )
                }
            }

        }

    }

}