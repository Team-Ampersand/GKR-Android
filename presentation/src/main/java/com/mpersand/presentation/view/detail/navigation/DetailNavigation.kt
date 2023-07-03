package com.mpersand.presentation.view.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mpersand.presentation.view.detail.DetailScreen

const val detailRoute = "detail_route"

fun NavController.navigateToDetail(productNumber: String) {
    this.navigate("$detailRoute/$productNumber")
}

fun NavGraphBuilder.detailScreen() {
    composable("$detailRoute/{productNumber}") { navBackStackEntry ->
        DetailScreen(productNumber = navBackStackEntry.arguments?.getString("productNumber"))
    }
}