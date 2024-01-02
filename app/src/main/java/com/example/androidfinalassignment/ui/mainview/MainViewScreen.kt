package com.example.androidfinalassignment.ui.mainview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidfinalassignment.ui.mainviewsearch.MainSearchViewBody
import com.example.androidfinalassignment.ui.mainviewsearch.MainViewSearchUiState
import com.example.androidfinalassignment.ui.mainviewsearch.MainViewSearchViewModel
import com.example.androidfinalassignment.ui.util.RecipeCard

@ExperimentalMaterial3Api
@Composable
fun MainViewScreenView(
    navControllerUnregistered: NavController,
    navControllerRegistered:  NavHostController,
    mainViewModelView : MainViewModelView
){
    val mainScreenUiState = mainViewModelView.uiState.collectAsState()

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold (
        containerColor = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        bottomBar = {
            MainViewBottomBar(
                navController = navControllerRegistered,
                mainViewModelView = mainViewModelView,
                mainScreenUiState = mainScreenUiState.value
            )
        },
        topBar = {
            MainViewTopBar(
                mainViewModelView = mainViewModelView,
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        NavHost(navController = navControllerRegistered, startDestination = "home") {
            composable("home") {
                MainHomeViewBody(
                    modifier = Modifier
                        .padding(innerPadding),
                    mainViewModelView = mainViewModelView,
                    mainScreenUiState = mainScreenUiState.value,
                    navControllerUnregistered = navControllerUnregistered
                )
            }
            composable("search") {
                val mainViewSearchViewModel: MainViewSearchViewModel = viewModel(factory = MainViewSearchViewModel.Factory)
                MainSearchViewBody(
                    modifier = Modifier
                        .padding(innerPadding),
                    mainViewSearchViewModel = mainViewSearchViewModel,
                    navControllerUnregistered = navControllerUnregistered
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainViewTopBar(
    mainViewModelView: MainViewModelView,
    scrollBehavior: TopAppBarScrollBehavior
) {
    CenterAlignedTopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        title = {
            Text(
                "MyMeals",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun MainHomeViewBody(
    modifier: Modifier = Modifier,
    mainViewModelView : MainViewModelView,
    mainScreenUiState: MainViewUiState,
    navControllerUnregistered: NavController
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 3.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = CenterHorizontally
    ){
        mainScreenUiState.meals.forEach { recipe ->
            item {
                RecipeCard(
                    recipe = recipe,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    navControllerUnregistered = navControllerUnregistered
                )
            }
        }
        item {
            Button(modifier = Modifier,
                onClick = { mainViewModelView.refreshMeals()}
            ) {
                Text("Refresh")
            }
        }
    }

}

@Composable
fun MainViewBottomBar(
    navController: NavController,
    mainViewModelView : MainViewModelView,
    mainScreenUiState: MainViewUiState
){
    BottomAppBar (
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .fillMaxWidth()
    ){
        Row (
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ){
            IconButton(
                modifier = Modifier,
                onClick = {
                    mainViewModelView.updateSelectedTab(MainViewTabs.HOME)
                    //mainViewModelView.tryParse()
                    //mainViewModelView.getMeals()
                    navController.navigate("home")
                }
            ){
                Icon(
                    imageVector = if (mainScreenUiState.selectedTab == MainViewTabs.HOME)  Icons.Filled.Home else Icons.Outlined.Home,
                    contentDescription = "Home",
                )
            }
            IconButton(
                modifier = Modifier,
                onClick = {
                    mainViewModelView.updateSelectedTab(MainViewTabs.SEARCH)
                    navController.navigate("search")
                }
            ){
                Icon(
                    imageVector = if (mainScreenUiState.selectedTab == MainViewTabs.SEARCH)  Icons.Filled.Search else Icons.Outlined.Search,
                    contentDescription = "Search recipes"
                )
            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun MainViewScreenPreview() {
    val navController = rememberNavController()
    val mainViewModelView: MainViewModelView = viewModel(factory = MainViewModelView.Factory)

    MainViewScreenView(
        navControllerRegistered = navController,
        navControllerUnregistered = navController,
        mainViewModelView = mainViewModelView
    )
}

