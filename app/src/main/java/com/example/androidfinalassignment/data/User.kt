package com.example.androidfinalassignment.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidfinalassignment.domain.Meal
import com.example.androidfinalassignment.domain.RecipeResponse

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val weight: Float,
    val height: Float,
    val age: String,
    val gender: String,
    val dailyCalories: Double,
    val foodAllergies: String,
    val cuisineDislikes: String,
    val dietPreference: String,
    val dailyRecipes: List<RecipeResponse> = emptyList(),
    val pastRecipes: List<RecipeResponse> = emptyList()
)



