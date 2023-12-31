package com.example.androidfinalassignment.ui.mainviewsearch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidfinalassignment.domain.Meal
import com.example.androidfinalassignment.ui.mainview.MainViewModelView
import com.example.androidfinalassignment.ui.mainview.MainViewUiState
import com.example.androidfinalassignment.ui.util.RecipeCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainSearchViewBody(modifier: Modifier, mainViewSearchViewModel: MainViewSearchViewModel) {

    val mainViewSearchUiState = mainViewSearchViewModel.uiState.collectAsState()
    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 4.dp)
                .padding(horizontal = 8.dp),
            value = mainViewSearchUiState.value.query,
            onValueChange = {mainViewSearchViewModel.updateSearchQuery(it)},
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                cursorColor = Color.White,
                textColor = Color.White,
                containerColor = MaterialTheme.colorScheme.secondary
            ),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.White
                )
            },
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(mainViewSearchUiState.value.foundMeals.size){
                RecipeCard(modifier = Modifier, meal = mainViewSearchUiState.value.foundMeals[it])
            }
        }

    }
}


@Composable
@Preview(showBackground = true)
fun MainSearchViewBodyPreview() {
    val mainViewSearchViewModel: MainViewSearchViewModel = viewModel(factory = MainViewSearchViewModel.Factory)

    MainSearchViewBody(modifier = Modifier, mainViewSearchViewModel = mainViewSearchViewModel)
}