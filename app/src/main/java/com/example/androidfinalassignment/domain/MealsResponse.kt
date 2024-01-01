package com.example.androidfinalassignment.domain

import android.provider.ContactsContract.CommonDataKinds.Im
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class MealsResponse (
    @SerialName("meals")
    val meals: List<MealSerial>,
    @SerialName("nutrients")
    val nutrients: Nutrients
)

@Serializable
data class MealSerial (
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("imageType")
    val imageType: String,
    @SerialName("readyInMinutes")
    val readyInMinutes: Int,
    @SerialName("servings")
    val servings: Int,
    @SerialName("sourceUrl")
    val sourceUrl: String
)


@Serializable
data class Nutrients (
    @SerialName("calories")
    val calories: Double,
    @SerialName("protein")
    val protein: Double,
    @SerialName("fat")
    val fat: Double,
    @SerialName("carbohydrates")
    val carbohydrates: Double
)