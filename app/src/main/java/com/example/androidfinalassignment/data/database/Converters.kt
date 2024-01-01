package com.example.androidfinalassignment.data.database

import androidx.room.TypeConverter
import com.example.androidfinalassignment.domain.Meal
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    private val json = Json { ignoreUnknownKeys = true
    isLenient = true }

    @TypeConverter
    fun fromMealList(value: String?): List<Meal> {
        return value?.let { json.decodeFromString<List<Meal>>(it) } ?: emptyList()
    }

    @TypeConverter
    fun toMealList(value: List<Meal>?): String {
        return json.encodeToString(value ?: emptyList())
    }
}