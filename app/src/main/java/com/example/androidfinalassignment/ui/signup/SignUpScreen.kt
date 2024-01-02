package com.example.androidfinalassignment.ui.signup

import android.content.pm.ActivityInfo
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavController,
    signUpViewModel: SignUpViewModel
) {

    val signUpUiState by signUpViewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()


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
                    value = signUpUiState.name,
                    onValueChange = { signUpViewModel.updateName(it) },
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
                    value = signUpUiState.weight,
                    onValueChange = { signUpViewModel.updateWeight(it) },
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
                    text = "Weight: ${signUpUiState.weight.toInt()} kg",
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Height
                Slider(
                    value = signUpUiState.height,
                    onValueChange = { signUpViewModel.updateHeight(it) },
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
                    text = "Height: ${signUpUiState.height.toInt()} cm",
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
                    items(SexOptionsRadio.values()) { sex ->

                        RadioButton(
                            selected = signUpUiState.genderSelection == sex,
                            onClick = { signUpViewModel.updateGenderSelection(sex) },
                            modifier = Modifier
                        )
                        Text(
                            text = sex.toString().capitalize(),
                            color = Color.White
                        )
                    }
                }

                // Age
                OutlinedTextField(
                    value = signUpUiState.age,
                    onValueChange = { signUpViewModel.updateAge(it) },
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
                    items(DietPreferences.values()) { diet ->

                        RadioButton(
                            selected = signUpUiState.dietPreferenceSelection == diet,
                            onClick = { signUpViewModel.updateDietPreference(diet) },
                            modifier = Modifier
                        )
                        Text(
                            text = diet.toString().capitalize(),
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

                    items(FoodAllergies.values()) { allergy ->
                        Button(
                            onClick = {
                                if (!signUpUiState.foodAllergies.contains(allergy))
                                    signUpViewModel.addFoodAllergy(allergy)

                                else
                                    signUpViewModel.removeFoodAllergy(allergy)
                            },
                            modifier = Modifier.padding(end = 8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (signUpUiState.foodAllergies.contains(allergy)) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                                contentColor = if (signUpUiState.foodAllergies.contains(allergy)) Color.White else MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(allergy.toString().capitalize())
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

                    items(CuisineDislikes.values()) { cuisine ->
                        Button(
                            onClick = {
                                if (!signUpUiState.cuisineDislikes.contains(cuisine))
                                    signUpViewModel.addCuisineDislike(cuisine)
                                else
                                    signUpViewModel.removeCuisineDislike(cuisine)
                            },
                            modifier = Modifier.padding(end = 8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (signUpUiState.cuisineDislikes.contains(cuisine)) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                                contentColor = if (signUpUiState.cuisineDislikes.contains(cuisine)) Color.White else MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(cuisine.toString().capitalize())
                        }
                    }
                }
            }
            item {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            signUpViewModel.createUser()
                        }
                        navController.navigate("mainViewScreen",builder = {
                            // Specify the popUpTo and popUpToInclusive arguments
                            popUpTo("signUpScreen") {
                                // Set popUpToInclusive to true if you want to include the onBoardScreens destination
                                inclusive = false
                            }
                        })
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
    val navController = rememberNavController()
    val signUpViewModel: SignUpViewModel = viewModel(factory = SignUpViewModel.Factory)
    SignUpScreen(navController = navController, signUpViewModel = signUpViewModel)
}