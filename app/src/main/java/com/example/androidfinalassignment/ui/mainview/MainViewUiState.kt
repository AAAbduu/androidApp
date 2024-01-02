package com.example.androidfinalassignment.ui.mainview

import com.example.androidfinalassignment.domain.RecipeResponse


/**
 * MainViewUiState is a data class that holds the state of the main view.
 */
data class MainViewUiState (
    var selectedTab: MainViewTabs = MainViewTabs.HOME,
    var meals: List<RecipeResponse> = listOf()
)

