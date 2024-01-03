package com.example.androidfinalassignment.ui.onboard

import OnBoardView
import android.content.pm.ActivityInfo
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
import androidx.navigation.NavController
import com.example.androidfinalassignment.R


/**
 * OnBoardScreens is the screen that is shown when the app is opened for the first time.
 */
@Composable
fun OnBoardScreens(
    navController: NavController
){
    val onBoardViews = OnBoardViewsList.valuesList()

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
            navController = navController,
            currentPage = currentView.value,
            noOfPages = onBoardViews.size
        ) {
            currentView.value++
        }
    }
}





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
    modifier: Modifier = Modifier, navController: NavController, currentPage: Int, noOfPages: Int, onNextClicked: () -> Unit
) {
    Button(
        onClick = {
            if (currentPage < noOfPages - 1) {
                onNextClicked()
            } else {
                navController.navigate("signUpScreen")
            }
        }, modifier = modifier
    ) {
        Text(text = if (currentPage < noOfPages - 1) "Next" else "Get Started")
    }
    Spacer(modifier = Modifier.height(40.dp))
}
