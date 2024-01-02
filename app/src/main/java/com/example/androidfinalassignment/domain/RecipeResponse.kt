package com.example.androidfinalassignment.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A data class representing a Recipe Response from the API, including detailed information about
 * cooking, ingredients, equipment and instructions.
 */
@Serializable
data class RecipeResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String = "",
    @SerialName("readyInMinutes")
    val readyInMinutes: Int = 0,
    @SerialName("servings")
    val servings: Int = 0,
    @SerialName("sourceUrl")
    val sourceUrl: String = "",
    @SerialName("image")
    val image: String = "",
    @SerialName("imageType")
    val imageType: String = "",
    @SerialName("nutrition")
    val nutrition: Nutrition,
    @SerialName("instructions")
    val instructions: String = "",
    @SerialName("analyzedInstructions")
    val analyzedInstructions: List<AnalyzedInstruction> = listOf(),
)
@Serializable
data class Nutrient(
    @SerialName("name")
    val name: String = "",
    @SerialName("amount")
    val amount: Double = 0.0,
    @SerialName("unit")
    val unit: String = "",
    @SerialName("percentOfDailyNeeds")
    val percentOfDailyNeeds: Double = 0.0
)

@Serializable
data class Nutrition(
    @SerialName("nutrients")
    val nutrients: List<Nutrient> = listOf(),
)

@Serializable
data class AnalyzedInstruction(
    @SerialName("name")
    val name: String = "",
    @SerialName("steps")
    val steps: List<InstructionStep> = listOf()
)

@Serializable
data class InstructionStep(
    @SerialName("number")
    val number: Int = 0,
    @SerialName("step")
    val step: String = "",
    @SerialName("ingredients")
    val ingredients: List<Ingredient> = listOf(),
    @SerialName("equipment")
    val equipment: List<Equipment> = listOf()
)

@Serializable
data class Ingredient(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("name")
    val name: String = "",
    @SerialName("localizedName")
    val localizedName: String = "",
    @SerialName("image")
    val image: String = ""
)

@Serializable
data class Equipment(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("name")
    val name: String = "",
    @SerialName("localizedName")
    val localizedName: String = "",
    @SerialName("image")
    val image: String = ""
)