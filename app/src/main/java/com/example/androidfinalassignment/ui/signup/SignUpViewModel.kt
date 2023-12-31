package com.example.androidfinalassignment.ui.signup

import androidx.lifecycle.ViewModel
import com.example.androidfinalassignment.data.User
import com.example.androidfinalassignment.domain.RecipeResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.json.Json


class SignUpViewModel() : ViewModel(){

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState = _uiState.asStateFlow()


    fun updateName(name: String) {
        _uiState.value = _uiState.value.copy(name = name)
    }

    fun updateWeight(weight: Float) {
        _uiState.value = _uiState.value.copy(weight = weight)
    }

    fun updateHeight(height: Float) {
        _uiState.value = _uiState.value.copy(height = height)
    }

    fun updateAge(age: String) {
        _uiState.value = _uiState.value.copy(age = age)
    }

    fun updateGenderSelection(genderSelection: SexOptionsRadio) {
        _uiState.value = _uiState.value.copy(genderSelection = genderSelection)
    }

    fun updateMealNumbers(mealNumbers: String) {
        _uiState.value = _uiState.value.copy(mealNumbers = mealNumbers.toInt())
    }

    fun updateDietPreference(dietPreferenceSelection: DietPreferences) {
        _uiState.value = _uiState.value.copy(dietPreferenceSelection = dietPreferenceSelection)
    }

    fun addFoodAllergy(foodAllergy: FoodAllergies) {
        val foodAllergies = _uiState.value.foodAllergies.toMutableList()
        foodAllergies.add(foodAllergy)
        _uiState.value = _uiState.value.copy(foodAllergies = foodAllergies)
    }

    fun removeFoodAllergy(foodAllergy: FoodAllergies) {
        val foodAllergies = _uiState.value.foodAllergies.toMutableList()
        foodAllergies.remove(foodAllergy)
        _uiState.value = _uiState.value.copy(foodAllergies = foodAllergies)
    }

    fun addCuisineDislike(cuisineDislike: CuisineDislikes) {
        val cuisineDislikes = _uiState.value.cuisineDislikes.toMutableList()
        cuisineDislikes.add(cuisineDislike)
        _uiState.value = _uiState.value.copy(cuisineDislikes = cuisineDislikes)
    }

    fun removeCuisineDislike(cuisineDislike: CuisineDislikes) {
        val cuisineDislikes = _uiState.value.cuisineDislikes.toMutableList()
        cuisineDislikes.remove(cuisineDislike)
        _uiState.value = _uiState.value.copy(cuisineDislikes = cuisineDislikes)
    }



    fun collectUserData(
        name: String = "anonymous",
        weight: Float = 60f,
        height: Float = 150f,
        age: String = "18",
        gender: String = "male",
        numberOfMeals: Int = 3,
        dietPreference: String = "balanced",
        foodAllergies: List<String> = emptyList(),
        cuisineDislikes: List<String> = emptyList(),
    ) {
        var BMR: Double? = null

        BMR = if (gender.equals("male")){
            10 * weight + 6.25 * height - 5 * age.toInt() + 5
        } else {
            10 * weight + 6.25 * height - 5 * age.toInt() - 161
        }
        var dailyCalories = BMR?.times(1.2)

        val user = User(
            name = name,
            weight = weight,
            height = height,
            age = age,
            gender = gender,
            dailyCalories = dailyCalories?.toFloat()!!,
            numberOfMeals = numberOfMeals,
            foodAllergies = foodAllergies,
            cuisineDislikes = cuisineDislikes,
            dietPreference = dietPreference,
            dailyRecipes = emptyList(),
            pastRecipes = emptyList()
        )

    }

}