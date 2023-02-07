package com.danisbana.danisbanaapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.danisbana.danisbanaapp.presentation.screen.home.root.HomeRoute
import com.danisbana.danisbanaapp.presentation.screen.splash.SplashRoute

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route){ SplashRoute(navController = navController) }
        composable(route = Screen.Home.route) { HomeRoute() }
    }
}