package com.mpersand.presentation.view.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mpersand.presentation.view.main.MainScreen

const val mainRoute = "main_route"

fun NavController.navigateToMain() {
    this.navigate(mainRoute)
}

fun NavGraphBuilder.mainScreen(
    navigateToDetail: (productNumber: String) -> Unit,
    navigateToProfile: () -> Unit,
    navigateToSearch: () -> Unit
) {
    composable(mainRoute) {
        MainScreen(
            navigateToDetail = navigateToDetail,
            navigateToProfile = navigateToProfile,
            navigateToSearch = navigateToSearch
        )
    }
}