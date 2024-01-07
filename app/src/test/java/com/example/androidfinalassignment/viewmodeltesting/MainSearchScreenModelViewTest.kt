package com.example.androidfinalassignment.viewmodeltesting

import com.example.androidfinalassignment.fake.FakeCentralRepository
import com.example.androidfinalassignment.ui.mainviewsearch.MainViewSearchUiState
import com.example.androidfinalassignment.ui.mainviewsearch.MainViewSearchViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class MainSearchScreenModelViewTest {

    private lateinit var mainViewSearchViewModel: MainViewSearchViewModel
    private lateinit var mainViewSearchUiState: MainViewSearchUiState
    private lateinit var centralRepository: FakeCentralRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        centralRepository = FakeCentralRepository()

        mainViewSearchViewModel= MainViewSearchViewModel(centralRepository)

        mainViewSearchUiState = mainViewSearchViewModel.uiState.value
    }

    /**
     * Check if the query is received and feeded to the uiState
     */
    @Test
    fun testQueryIsReceivedAndExecuted() = runTest {
        mainViewSearchViewModel.updateSearchQuery("test")

        val result = centralRepository.getAutocompletedTitleRecipe(
            query = mainViewSearchUiState.query,
            number = 1
        )
        val resultBody = result.body()?.string()

        if (resultBody != null) {
            assert(resultBody.contains("test"))
        }
    }

}