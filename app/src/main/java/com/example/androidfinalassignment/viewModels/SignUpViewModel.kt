package com.example.androidfinalassignment.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.androidfinalassignment.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class SignUpViewModel : ViewModel() {

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
        )

    }

}