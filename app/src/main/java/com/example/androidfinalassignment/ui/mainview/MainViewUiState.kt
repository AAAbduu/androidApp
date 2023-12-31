package com.example.androidfinalassignment.ui.mainview

import com.example.androidfinalassignment.domain.Meal

data class MainViewUiState (
    var name: String = "",
    var selectedTab: MainViewTabs = MainViewTabs.HOME,
    var meals: List<Meal> = listOf()
)

