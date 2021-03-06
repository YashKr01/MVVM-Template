package com.example.sample.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val title: String,
    val priority: String,
    val category: String,
    val date: String
) : Parcelable
