package com.example.sample.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.getWeatherResponse("delhi")
        }
    }

    val weatherResponse get() = repository.weatherResponse

    fun getWeatherResponse(name: String) = viewModelScope.launch {
        repository.getWeatherResponse(name)
    }

}