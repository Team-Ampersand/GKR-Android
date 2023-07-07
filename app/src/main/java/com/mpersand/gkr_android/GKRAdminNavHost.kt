package com.mpersand.gkr_android

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mpersand.presentation.view.detail.navigation.detailScreen
import com.mpersand.presentation.view.detail.navigation.navigateToDetail
import com.mpersand.presentation.view.main.navigation.mainScreen
import com.mpersand.presentation.view.main.navigation.navigateToMain
import com.mpersand.presentation.view.profile.navigation.navigateProfile
import com.mpersand.presentation.view.profile.navigation.profileScreen
import com.mpersand.presentation.view.search.navigation.navigateToSearch
import com.mpersand.presentation.view.search.navigation.searchScreen
import com.mpersand.presentation.view.signin.navigation.navigateToSignIn
import com.mpersand.presentation.view.signin.navigation.signInScreen

@Composable
fun GKRNavHost(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination) {
        signInScreen(navigateToMain = {
            navController.navigateToMain()
        })

        mainScreen(
            navigateToDetail = { navController.navigateToDetail(it) },
            navigateToProfile = { navController.navigateProfile() },
            navigateToSearch = { navController.navigateToSearch() }
        )

        profileScreen(
            navigateToMain = { navController.navigateToMain() },
            navigateToSignIn = { navController.navigateToSignIn() }
        )

        detailScreen()

        searchScreen(
            navigateToMain = { navController.navigateToMain() },
            navigateToDetail = { navController.navigateToDetail(it) }
        )
    }
}