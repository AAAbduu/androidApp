package com.example.androidfinalassignment.views.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

data class SignUpUiState(
    var name: String = "",
    var genderSelection: String = "",
    var weight: Float = 60f,
    var height: Float = 150f,
    var age: String = "18",
    var mealNumbers: Int = 3,
    var dietPreferenceSelection: String = "",
    var foodAllergies: List<String> = emptyList(),
    var cuisineDislikes: List<String> = emptyList()
)