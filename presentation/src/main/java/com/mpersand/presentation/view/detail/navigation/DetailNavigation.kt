package com.mpersand.presentation.view.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mpersand.presentation.view.detail.DetailScreen

const val detailRoute = "detail_route"

fun NavController.navigateToDetail(productNumber: String) {
    this.navigate("$detailRoute/$productNumber")
}

fun NavGraphBuilder.detailScreen() {
    composable(
        route = "$detailRoute/{productNumber}",
        arguments = listOf(navArgument("productNumber") { type = NavType.StringType })
    ) { navBackStackEntry ->
        DetailScreen(productNumber = navBackStackEntry.arguments?.getString("productNumber"))
    }
}