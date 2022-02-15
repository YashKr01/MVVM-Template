package com.example.sample.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.getNewsList()
        }
    }

    val newsList get() = repository.newsList

    fun getNewsList() = viewModelScope.launch {
        repository.getNewsList()
    }

}