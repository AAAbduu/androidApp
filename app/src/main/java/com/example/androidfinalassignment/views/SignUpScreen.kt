package com.example.androidfinalassignment.views

import android.widget.RadioGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidfinalassignment.viewModels.SignUpViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen() {
    val sexOptionsRadio = listOf(
        "male",
        "female"
    )

    val foodAllergiesList = listOf(
        "dairy",
        "egg",
        "gluten",
        "peanut",
        "sesame",
        "seafood",
        "shellfish",
        "soy",
        "sulfite",
        "tree nut",
        "wheat"
    )

    val dietPreferenceList = listOf(
        "pescetarian",
        "lacto vegetarian",
        "ovo vegetarian",
        "vegan",
        "paleo",
        "primal",
        "vegetarian"
    )

    val cuisineDislikesList = listOf(
        "african",
        "american",
        "british",
        "cajun",
        "caribbean",
        "chinese",
        "eastern european",
        "european",
        "french",
        "german",
        "greek",
        "indian",
        "irish",
        "italian",
        "japanese",
        "jewish",
        "korean",
        "latin american",
        "mediterranean",
        "mexican",
        "middle eastern",
        "nordic",
        "southern",
        "spanish",
        "thai",
        "vietnamese"
    )

    val signUpViewModel = SignUpViewModel()
    var name by remember { mutableStateOf("Name") }
    var genderSelection by remember { mutableStateOf(sexOptionsRadio[0]) }
    var weight by remember { mutableStateOf(60f) }
    var height by remember { mutableStateOf(170f) }
    var age by remember { mutableStateOf("18") }
    var mealNumbers by remember { mutableStateOf("3") }
    var dietPreferenceSelection by remember { mutableStateOf(dietPreferenceList[0]) }
    var foodAllergies by remember { mutableStateOf(emptyList<String>()) }
    var cuisineDislikes by remember { mutableStateOf(emptyList<String>()) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Name
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                // Name
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name", color = Color.White) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    textStyle = TextStyle(color = Color.White),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        capitalization = KeyboardCapitalization.Words,
                        keyboardType = KeyboardType.Text
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.secondary
                    )
                )

                // Weight
                Slider(
                    value = weight,
                    onValueChange = { weight = it },
                    valueRange = 0f..200f,
                    steps = 100,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.primary
                    )
                )
                Text(
                    text = "Weight: ${weight.toInt()} kg",
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Height
                Slider(
                    value = height,
                    onValueChange = { height = it },
                    valueRange = 0f..250f,
                    steps = 100,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.primary
                    )
                )
                Text(
                    text = "Height: ${height.toInt()} cm",
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                //Sex selection with a radio group dislayed within 1 row
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items(sexOptionsRadio) { sex ->

                        RadioButton(
                            selected = genderSelection == sex,
                            onClick = { genderSelection = sex },
                            modifier = Modifier
                        )
                        Text(
                            text = sex.capitalize(),
                            color = Color.White
                        )
                    }
                }

                // Age
                OutlinedTextField(
                    value = age,
                    onValueChange = { age = it },
                    label = { Text("Age", color = Color.White) },
                    textStyle = TextStyle(color = Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        capitalization = KeyboardCapitalization.Words,
                        keyboardType = KeyboardType.Number
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.secondary
                    )
                )
                Text(
                    text = "How many meals would you like to have per day?",
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                OutlinedTextField(
                    value = mealNumbers,
                    onValueChange = { mealNumbers = it },
                    label = { Text("Nº Meals", color = Color.White) },
                    textStyle = TextStyle(color = Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        capitalization = KeyboardCapitalization.Words,
                        keyboardType = KeyboardType.Number
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.secondary
                    )
                )
            }
            item {
                Text(
                    text = "Do you have any diet preference?",
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items(dietPreferenceList) { diet ->

                        RadioButton(
                            selected = dietPreferenceSelection == diet,
                            onClick = { dietPreferenceSelection = diet },
                            modifier = Modifier
                        )
                        Text(
                            text = diet.capitalize(),
                            color = Color.White
                        )
                    }
                }
            }
            item {
                Text(
                    text = "Do you have any food allergies?",
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Food Allergies displayed in a lazy row, and once selected, add to the allergy list
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {

                    items(foodAllergiesList) { allergy ->
                        Button(
                            onClick = {
                                if (foodAllergies.contains(allergy))
                                    foodAllergies = foodAllergies - allergy
                                else
                                    foodAllergies = foodAllergies + allergy
                            },
                            modifier = Modifier.padding(end = 8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (foodAllergies.contains(allergy)) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                                contentColor = if (foodAllergies.contains(allergy)) Color.White else MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(allergy.capitalize())
                        }
                    }
                }

                Text(
                    text = "Do you dislike any cuisine in particular?",
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // LazyRow for cuisine dislikes
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {

                    items(cuisineDislikesList) { cuisine ->
                        Button(
                            onClick = {
                                if (cuisineDislikes.contains(cuisine))
                                    cuisineDislikes = cuisineDislikes - cuisine
                                else
                                    cuisineDislikes = cuisineDislikes + cuisine
                            },
                            modifier = Modifier.padding(end = 8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (cuisineDislikes.contains(cuisine)) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                                contentColor = if (cuisineDislikes.contains(cuisine)) Color.White else MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(cuisine.capitalize())
                        }
                    }
                }
            }
            item {
                Button(
                    onClick = {
                        signUpViewModel.collectUserData(
                            name = name,
                            weight = weight,
                            height = height,
                            age = age,
                            gender = genderSelection,
                            numberOfMeals = mealNumbers.toInt(),
                            dietPreference = dietPreferenceSelection,
                            foodAllergies = foodAllergies,
                            cuisineDislikes = cuisineDislikes
                        )
                    },
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                ) {
                    Text("Start")
                }
            }
        }
    }
}



@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}