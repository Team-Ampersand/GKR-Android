package com.mpersand.presentation.view.signin.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mpersand.presentation.view.signin.SignInScreen

const val signInRoute = "signin_route"

fun NavGraphBuilder.signInScreen() {
    composable(signInRoute) {
        SignInScreen()
    }
}