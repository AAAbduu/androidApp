package com.example.androidfinalassignment.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A data class representing a meal obtained by suggestion of the API reponse.
 */
@Serializable
data class AutocompleteMeal(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("imageType")
    val imageType: String
)