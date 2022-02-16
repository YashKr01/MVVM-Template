package com.example.sample.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample.model.TaskEntity
import com.example.sample.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {

    fun getTasksList() = repository.getAllTasks()

    fun deleteArticle(task: TaskEntity) = viewModelScope.launch {
        repository.deleteArticle(task)
    }

}