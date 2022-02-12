package com.example.sample.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val current: Current,
    val location: Location
)

data class Condition(
    @SerializedName("icon")
    val icon: String,
    @SerializedName("text")
    val condition: String
)

data class Location(
    @SerializedName("country")
    val country: String,
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("lon")
    val longitude: Double,
    @SerializedName("name")
    val name: String,
)

data class Current(
    @SerializedName("feelslike_c")
    val feelsLike: Double,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("temp_c")
    val temperature: Double,
    @SerializedName("wind_kph")
    val windSpeed: Double,
    @SerializedName("condition")
    val condition: Condition
)