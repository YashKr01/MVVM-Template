package com.example.sample.network

import com.example.sample.model.NewsResponse
import com.example.sample.model.WeatherResponse
import com.example.sample.utils.Constants.WEATHER_API_KEY
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiInterface {

    @GET
    suspend fun getTopHeadlines(@Url newsUrl: String): NewsResponse

    @GET("forecast.json?key=$WEATHER_API_KEY")
    suspend fun getWeatherResponse(@Query("q") name: String) : WeatherResponse

}