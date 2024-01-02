package com.example.androidfinalassignment.ui.detailedrecipeview

/**
 * DetailedRecipeUiState is a data class that holds the state of the detailed recipe view.
 */
data class DetailedRecipeUiState(
    val name: String = "",
    val ingredients: List<String> = listOf(),
    val instructions: String = "",
    val image: String = "",
    val equipment: List<String> = listOf()
)