package com.abc.moscow.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abc.moscow.ui.layout.CategoryScreen
import com.abc.moscow.ui.layout.PlaceScreen
import com.abc.moscow.ui.theme.MoscowTheme
import com.abc.moscow.utils.AppDestinations

@Composable
fun MoscowApp(windowSize: WindowWidthSizeClass) {
    val viewModel = viewModel(AppViewModel::class)
    val uiState by viewModel.uiState.collectAsState()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppDestinations.CATEGORY.name,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .displayCutoutPadding()
    ) {
        // Routes:

        composable(route = AppDestinations.CATEGORY.name) {
            CategoryScreen(
                windowSize = windowSize,
                categories = uiState.categories,
                onCategoryClick = {
                    viewModel.setCategory(it)
                    navController.navigate(AppDestinations.PLACE.name)
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(route = AppDestinations.PLACE.name) {
            PlaceScreen(
                windowSize = windowSize,
                isShowingListPage = uiState.isShowingListPage,
                category = uiState.currentCategory,
                places = uiState.currentPlaceList,
                selectedPlace = uiState.currentPlace,
                onPlaceClick = {
                    viewModel.setPlace(it)
                },
                onBackButtonClick = {
                    navController.navigateUp()
                },
                onPlaceScreenBackButtonClick = {
                    viewModel.placeScreenBackButtonClick()
                },
                modifier = Modifier.fillMaxSize()
            )
        }

    }
}



@Preview
@Composable
fun PreviewMoscowApp() {
    MoscowTheme {
        MoscowApp(WindowWidthSizeClass.Medium)
    }
}