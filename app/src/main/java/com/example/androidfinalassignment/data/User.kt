package com.example.androidfinalassignment.data

import com.example.androidfinalassignment.domain.Meal

data class User(
    val name: String,
    val weight: Float,
    val height: Float,
    val age: String,
    val gender: String,
    val dailyCalories: Float,
    val numberOfMeals: Int,
    val foodAllergies: List<String>,
    val cuisineDislikes: List<String>,
    val dietPreference: String,
    val dailyRecipes: List<Meal>,
    val pastRecipes: List<Meal>
)



