package com.example.sample.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample.model.CategoryEntity
import com.example.sample.model.TaskEntity
import com.example.sample.repository.AppRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class TaskAddUpdateViewModel @Inject constructor(private val repository: AppRepository) :
    ViewModel() {

    fun getAllCategories() = viewModelScope.launch {
        repository.getAllCategories()
    }

    fun insertTask(taskEntity: TaskEntity) = viewModelScope.launch {
        repository.insertArticle(taskEntity)
    }

    fun insertCategory(categoryEntity: CategoryEntity) = viewModelScope.launch {
        repository.insertCategory(categoryEntity)
    }

}