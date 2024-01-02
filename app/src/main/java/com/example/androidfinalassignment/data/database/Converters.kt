package com.example.androidfinalassignment.data.database

import androidx.room.TypeConverter
import com.example.androidfinalassignment.domain.Meal
import com.example.androidfinalassignment.domain.RecipeResponse
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * A class that converts a list of [RecipeResponse] to a [String] and vice versa.
 */
class Converters {
    private val json = Json { ignoreUnknownKeys = true
    isLenient = true }

    @TypeConverter
    fun fromRecipeResponseList(recipeResponseList: List<RecipeResponse>): String {
        return json.encodeToString(recipeResponseList)
    }

    @TypeConverter
    fun toRecipeResponseList(recipeResponseListString: String): List<RecipeResponse> {
        return json.decodeFromString(recipeResponseListString)
    }

}