package com.example.androidfinalassignment.ui.mainviewsearch

import com.example.androidfinalassignment.domain.Meal

data class MainViewSearchUiState(
    var query: String = "",
    var foundMeals: List<Meal> = listOf()
)