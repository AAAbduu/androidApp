package com.example.androidfinalassignment.ui.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

data class SignUpUiState(
    var name: String = "",
    var genderSelection: SexOptionsRadio = SexOptionsRadio.MALE,
    var weight: Float = 60f,
    var height: Float = 150f,
    var age: String = "18",
    var mealNumbers: Int = 3,
    var dietPreferenceSelection: DietPreferences = DietPreferences.pescatarian,
    var foodAllergies: List<FoodAllergies> = emptyList(),
    var cuisineDislikes: List<CuisineDislikes> = emptyList()
)