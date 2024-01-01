package com.example.androidfinalassignment.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidfinalassignment.FinalAssignmentApplication
import com.example.androidfinalassignment.data.User
import com.example.androidfinalassignment.data.CentralRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.File.separator


class SignUpViewModel(/*private val mealsRepository: MealsRepository,*/ private val centralRepository: CentralRepository) : ViewModel(){

    init {
        //deleteAllUsers()
    }

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



    suspend fun createUser(
    ) {
        var BMR: Double? = null

        BMR = if (uiState.value.genderSelection.equals("male")){
            10 * uiState.value.weight + 6.25 * uiState.value.height - 5 * uiState.value.age.toInt() + 5
        } else {
            10 * uiState.value.weight + 6.25 * uiState.value.height - 5 * uiState.value.age.toInt() - 161
        }
        var dailyCalories = BMR?.times(1.2)

        val user = User(
            name = uiState.value.name,
            weight = uiState.value.weight,
            height = uiState.value.height,
            age = uiState.value.age,
            gender = uiState.value.genderSelection.toString(),
            dailyCalories = dailyCalories!!,
            foodAllergies = uiState.value.foodAllergies.joinToString(separator = ", "),
            cuisineDislikes = uiState.value.cuisineDislikes.joinToString(separator = ", "),
            dietPreference = uiState.value.dietPreferenceSelection.toString(),
            dailyRecipes = emptyList(),
            pastRecipes = emptyList()
        )

        centralRepository.insertUser(user)

    }

    private fun deleteAllUsers() {
        viewModelScope.launch {
            centralRepository.deleteAllUsers()
        }
    }
    companion object {
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FinalAssignmentApplication)
                /*val myMealsRepository = application.container.mealRepository*/
                val centralRepository = application.container.centralRepository
                SignUpViewModel(/*myMealsRepository,*/ centralRepository)
            }
        }
    }

}