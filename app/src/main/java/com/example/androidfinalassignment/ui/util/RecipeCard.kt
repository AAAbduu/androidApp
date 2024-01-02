package com.example.androidfinalassignment.ui.util

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.androidfinalassignment.domain.AnalyzedInstruction
import com.example.androidfinalassignment.domain.Equipment
import com.example.androidfinalassignment.domain.Ingredient
import com.example.androidfinalassignment.domain.InstructionStep
import com.example.androidfinalassignment.domain.Nutrient
import com.example.androidfinalassignment.domain.Nutrition
import com.example.androidfinalassignment.domain.RecipeResponse


/**
 * RecipeCard is a composable that displays a recipe card.
 * @param modifier Modifier to be applied to the layout.
 * @param modifier Modifier to be applied to the layout.
 */
@Composable
fun RecipeCard(
    modifier: Modifier,
    recipe: RecipeResponse,
    navControllerUnregistered: NavController
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp),
        modifier = Modifier
            .clickable { navControllerUnregistered.navigate("detailedRecipeScreen/${recipe.id}") }
        )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onSecondaryContainer),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(150.dp),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(recipe.image)
                    .crossfade(true)
                    .build(),
                contentDescription = "Recipe Image",
                contentScale = ContentScale.FillWidth
            )

                Text(
                    text = recipe.title,
                    color = Color.White,
                    modifier = Modifier
                        .padding(16.dp)
                )
                Text(
                    text = recipe.readyInMinutes.toString() + " minutes",
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
    val recipe = RecipeResponse(
        id = 479101,
        title = "On the Job: Pan Roasted Cauliflower From Food52",
        readyInMinutes = 20,
        servings = 4,
        sourceUrl = "http://feedmephoebe.com/2013/11/job-food52s-pan-roasted-cauliflower/",
        image = "https://spoonacular.com/recipeImages/479101-556x370.jpg",
        imageType = "jpg",
        nutrition = Nutrition(
            listOf(
                Nutrient("Sample Nutrient", 10.0, "g", 20.0)
            )
        ),
        instructions = "Cut the florets off the stems and and then chop them into tiny florets. You can also chop up the stems into tiny pieces if you want. You should have about 6 cups of chopped cauliflower. In a large skillet heat 2 tablespoons of olive oil over medium-high heat. Add the cauliflower, 1 teaspoon of salt, rosemary, and sumac. Sauté until cauliflower is tender and starts to brown a bit, stirring as necessary, about 15 minutes. You can also add a bit of olive oil if the pan starts to get too dry or the cauliflower is starting to stick. Meanwhile, in a small skillet, toast the pinenuts over medium heat until golden brown. Set aside. Heat the remaining 2 tablespoons of olive oil in the same pan. Once oil is shimmering, toss in the breadcrumbs and stir, toasting the breadcrumbs. Season with a pinch of kosher salt and a few turns of freshly ground black pepper. Remove from the heat and toss in half of the chopped parsley. When cauliflower is done, remove from the heat and season to taste with freshly ground black pepper and a pinch or so of salt if necessary. Toss in the toasted pine nuts, the chopped raisins, and the remaining parsley. When ready to serve, sprinkle the top with the toasted breadcrumbs and some pecorino.",
        analyzedInstructions = listOf(
            AnalyzedInstruction(
                "Sample Instruction",
                listOf(
                    InstructionStep(
                        1,
                        "Sample Step",
                        listOf(Ingredient(1, "Sample Ingredient", "Sample Ingredient", "https://example.com/ingredient.jpg")),
                        listOf(Equipment(1, "Sample Equipment", "Sample Equipment", "https://example.com/equipment.jpg"))
                    )
                )
            )
        )
    )

    val navControllerUnregistered = rememberNavController()

    RecipeCard(modifier = Modifier, recipe = recipe, navControllerUnregistered = navControllerUnregistered)
}