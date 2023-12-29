package com.example.androidfinalassignment.model

class User(
    name: String,
    weight: Float,
    height: Float,
    age: String,
    gender: String,
    dailyCalories: Float,
    numberOfMeals: Int,
    foodAllergies: List<String>,
    cuisineDislikes: List<String>,
    dietPreference: String
) {

    private var name: String = name
    private var weight: Float = weight
    private var height: Float = height
    private var age: String = age
    private var gender: String = gender
    private var dailyCalories: Float = dailyCalories
    private var numberOfMeals: Int = numberOfMeals
    private var foodAllergies: List<String> = foodAllergies
    private var cuisineDislikes: List<String> = cuisineDislikes
    private var dietPreference: String = dietPreference
    private var dailyMeals: List<Recipe> = emptyList()
    private var pastMeals: List<Recipe> = emptyList()
}

