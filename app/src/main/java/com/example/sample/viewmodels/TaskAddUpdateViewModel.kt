package com.example.sample.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample.model.CategoryEntity
import com.example.sample.model.TaskEntity
import com.example.sample.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskAddUpdateViewModel @Inject constructor(private val repository: AppRepository) :
    ViewModel() {

    fun insertTask(taskEntity: TaskEntity) = viewModelScope.launch {
        repository.insertArticle(taskEntity)
    }

}