package com.example.androidfinalassignment.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RecipeResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("readyInMinutes")
    val readyInMinutes: Int,
    @SerialName("servings")
    val servings: Int,
    @SerialName("sourceUrl")
    val sourceUrl: String,
    @SerialName("image")
    val image: String,
    @SerialName("imageType")
    val imageType: String,
    @SerialName("nutrition")
    val nutrition: Nutrition,
    @SerialName("instructions")
    val instructions: String,
    @SerialName("analyzedInstructions")
    val analyzedInstructions: List<AnalyzedInstruction>
)
@Serializable
data class Nutrient(
    @SerialName("name")
    val name: String,
    @SerialName("amount")
    val amount: Double,
    @SerialName("unit")
    val unit: String,
    @SerialName("percentOfDailyNeeds")
    val percentOfDailyNeeds: Float
)

@Serializable
data class Nutrition(
    @SerialName("nutrients")
    val nutrients: List<Nutrient>
)

@Serializable
data class AnalyzedInstruction(
    @SerialName("name")
    val name: String,
    @SerialName("steps")
    val steps: List<InstructionStep>
)

@Serializable
data class InstructionStep(
    @SerialName("number")
    val number: Int,
    @SerialName("step")
    val step: String,
    @SerialName("ingredients")
    val ingredients: List<Ingredient>,
    @SerialName("equipment")
    val equipment: List<Equipment>
)

@Serializable
data class Ingredient(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("localizedName")
    val localizedName: String,
    @SerialName("image")
    val image: String
)

@Serializable
data class Equipment(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("localizedName")
    val localizedName: String,
    @SerialName("image")
    val image: String
)