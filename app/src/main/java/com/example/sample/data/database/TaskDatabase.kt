package com.example.sample.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sample.model.CategoryEntity
import com.example.sample.model.TaskEntity

@Database(entities = [TaskEntity::class, CategoryEntity::class], exportSchema = false, version = 1)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

}