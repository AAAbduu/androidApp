package com.example.androidfinalassignment.ui.detailedrecipeview

data class DetailedRecipeUiState(
    val name: String = "",
    val ingredients: List<String> = listOf(),
    val instructions: String = "",
    val image: String = "",
    val equipment: List<String> = listOf()
)