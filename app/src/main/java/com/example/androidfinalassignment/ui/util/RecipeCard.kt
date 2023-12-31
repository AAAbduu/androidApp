package com.example.androidfinalassignment.ui.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidfinalassignment.R
import com.example.androidfinalassignment.domain.Meal
import okhttp3.internal.wait

@Composable
fun RecipeCard(
    modifier: Modifier,
    meal: Meal
    //recipeId: Int,
    //isExpanded: Boolean,
    //onRecipeClick: (Int) -> Unit,
    //onToggleExpand: () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp),
        modifier = Modifier
        )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onSecondaryContainer),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .height(150.dp),
                painter = painterResource(id = R.drawable.logo_color),
                contentDescription = "Logo",
                contentScale = ContentScale.FillWidth
            )

                Text(
                    text = meal.title,
                    color = Color.White,
                    modifier = Modifier
                        .padding(16.dp)
                )
                Text(
                    text = meal.readyInMinutes.toString() + " minutes",
                    color = Color.White,
                    modifier = Modifier
                        .padding(16.dp)
                )

        }

    }
}


@Composable
@Preview(showBackground = true)
fun RecipeCardPreview() {
    RecipeCard(modifier = Modifier, meal = Meal(1, "Chicken", "image", 10, 10, "url"))
}