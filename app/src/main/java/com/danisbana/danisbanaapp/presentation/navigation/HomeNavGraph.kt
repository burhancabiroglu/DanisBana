package com.danisbana.danisbanaapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.danisbana.danisbanaapp.presentation.screen.home.consultant.ConsultantRoute
import com.danisbana.danisbanaapp.presentation.screen.home.profile.ProfileRoute
import com.danisbana.danisbanaapp.presentation.screen.home.root.HomeRoute
import com.danisbana.danisbanaapp.presentation.screen.splash.SplashRoute

@Composable
fun SetupHomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Consultant.route
    ) {
        composable(route = Screen.Consultant.route) { HomeRoute() }
        composable(route = Screen.Consultant.route) { ConsultantRoute() }
        composable(route = Screen.Consultant.route) { ProfileRoute() }
    }
}