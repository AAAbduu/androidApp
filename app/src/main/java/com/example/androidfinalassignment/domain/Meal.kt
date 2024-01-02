package com.example.androidfinalassignment.domain

import kotlinx.serialization.Serializable

/**
 * A data class representing a meal.
 */

@Serializable
data class Meal(
    val id: Int,
    val title: String,
    val image: String,
    val imageType: String,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceUrl: String
)