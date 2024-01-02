package com.example.androidfinalassignment.ui.mainviewsearch

import com.example.androidfinalassignment.domain.RecipeResponse

data class MainViewSearchUiState(
    var query: String = "",
    var foundMeals: List<RecipeResponse> = listOf()
)