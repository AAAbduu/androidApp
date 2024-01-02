package com.example.androidfinalassignment.ui.detailedrecipeview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest

/**
 * DetailedRecipeScreen is a composable function that displays the detailed recipe view.
 *
 * @param detailedRecipeViewModel is the view model that holds the state of the detailed recipe view.
 * @param recipeId is the id of the recipe to be displayed.
 */

@Composable
fun DetailedRecipeScreen (detailedRecipeViewModel: DetailedRecipeViewModel, recipeId: Int){

    LaunchedEffect(recipeId) {
        detailedRecipeViewModel.getRecipe(recipeId)
    }
    val detailedRecipeUiState = detailedRecipeViewModel.uiState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            AsyncImage(
                modifier = Modifier
                    .height(250.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(16.dp)),

                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(detailedRecipeUiState.value.image)
                    .crossfade(true)
                    .build(),
                contentDescription = "Recipe Image",
                contentScale = ContentScale.FillWidth
            )
        }

        item {
            Text(
                text = detailedRecipeUiState.value.name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 16.dp),
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

        item {
            Text(
                text = "Ingredients: " + detailedRecipeUiState.value.ingredients.joinToString(", "),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp),
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }


        item {
            Text(
                text = "Equipment: " + detailedRecipeUiState.value.equipment.joinToString(", "),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp),
                color = Color.White,
                textAlign = TextAlign.Center

            )
        }

        item {
            Text(
                text = "Instructions",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 16.dp),
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

        item {
            Text(
                text = detailedRecipeUiState.value.instructions,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp),
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

    }


}



@Preview(showBackground = true)
@Composable
fun DetailedRecipeViewPreview() {
    val detailedRecipeViewModel: DetailedRecipeViewModel = viewModel(factory = DetailedRecipeViewModel.Factory)

    DetailedRecipeScreen(
        detailedRecipeViewModel = detailedRecipeViewModel,
        recipeId = 0
    )
}