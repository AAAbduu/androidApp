package com.example.androidfinalassignment.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AutocompleteMeal(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("imageType")
    val imageType: String
)