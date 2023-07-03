package com.mpersand.presentation.view.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mpersand.presentation.view.profile.ProfileScreen

const val profileRoute = "profile_route"

fun NavController.navigateProfile() {
    this.navigate(profileRoute)
}

fun NavGraphBuilder.profileScreen(
    navigateToMain: () -> Unit,
    navigateToSignIn: () -> Unit
) {
    composable(profileRoute) {
        ProfileScreen(
            navigateToMain = navigateToMain,
            navigateToSignIn = navigateToSignIn
        )
    }
}