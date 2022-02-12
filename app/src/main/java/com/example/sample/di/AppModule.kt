package com.example.sample.di

import android.content.Context
import androidx.room.Room
import com.example.sample.database.TaskDao
import com.example.sample.utils.Constants.DATABASE_NAME
import com.example.sample.database.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TaskDatabase =
        Room.databaseBuilder(context, TaskDatabase::class.java, DATABASE_NAME)
            .build()

    @Provides
    @Singleton
    fun provideDao(taskDatabase: TaskDatabase): TaskDao = taskDatabase.taskDao()

}