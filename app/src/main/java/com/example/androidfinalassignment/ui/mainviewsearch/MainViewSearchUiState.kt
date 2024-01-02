package com.example.androidfinalassignment.ui.mainviewsearch

import com.example.androidfinalassignment.domain.RecipeResponse

/**
 * MainViewSearchUiState is the state of the MainViewSearchScreen.
 */
data class MainViewSearchUiState(
    var query: String = "",
    var foundMeals: List<RecipeResponse> = listOf()
)