package com.example.sample.data.database

import androidx.room.TypeConverter
import java.util.*

class DateTypeConverters {

    @TypeConverter
    fun toDate(dateLong: Long?): Date? = dateLong?.let { Date(it) }

    @TypeConverter
    fun fromDate(date: Date?): Long? = date?.time

}