package com.example.androidfinalassignment.views.onboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.androidfinalassignment.R


@Composable
fun OnBoardScreens(){
    val onBoardViews = onBoardViewsList

    val currentView = remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {

        OnBoardBody(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            currentView = onBoardViews[currentView.value]
        )

        OnBoardNavButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp),
            currentPage = currentView.value,
            noOfPages = onBoardViews.size
        ) {
            currentView.value++
        }
    }
}


data class OnBoardView(
    val title: String,
    val description: String,
    val image: Int
)

val onBoardViewsList = listOf(
    OnBoardView(
        image = R.drawable.logo_color,
        title = "Welcome to MyMeals!",
        description = ""
    ), OnBoardView(
        image = R.drawable.on_board_view3,
        title = "Whole meal plan ready everyday!",
        description = "You will have a menu for the day prepared with as many meals as you selected and according to your necessities."
    ), OnBoardView(
        image = R.drawable.on_board_view2,
        title = "Personalized meals!",
        description = "Personalized meals according with your preferences and adopted to your food allergies."
    )
)

@Composable
fun OnBoardBody(
    modifier: Modifier = Modifier, currentView: OnBoardView
) {
    Column(
        modifier = modifier
    ) {
        OnBoardTitle(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            currentTitle = currentView.title
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = modifier) {
            Image(
                painter = painterResource(id = currentView.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(300.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        OnBoardText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = currentView.description
        )
    }
}

@Composable
fun OnBoardTitle(modifier: Modifier, currentTitle: String) {
    Spacer(modifier = Modifier.height(40.dp))
    Text(
        text = currentTitle,
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center,
        color = Color.White,
        modifier = modifier
    )
}

@Composable
fun OnBoardText(modifier: Modifier, text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center,
        color = Color.White,
        modifier = modifier
    )
}

@Composable
fun OnBoardNavButton(
    modifier: Modifier = Modifier, currentPage: Int, noOfPages: Int, onNextClicked: () -> Unit
) {
    Button(
        onClick = {
            if (currentPage < noOfPages - 1) {
                onNextClicked()
            } else {
                // Handle onboarding completion
            }
        }, modifier = modifier
    ) {
        Text(text = if (currentPage < noOfPages - 1) "Next" else "Get Started")
    }
    Spacer(modifier = Modifier.height(40.dp))
}
