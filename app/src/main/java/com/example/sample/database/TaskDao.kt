package com.example.sample.database

import androidx.room.*
import com.example.sample.model.TaskEntity
import com.example.sample.utils.Constants.DATABASE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM $DATABASE_NAME")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(taskEntity: TaskEntity)

    @Delete
    suspend fun deleteTask(taskEntity: TaskEntity)

}