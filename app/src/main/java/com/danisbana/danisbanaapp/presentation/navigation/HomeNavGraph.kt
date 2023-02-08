package com.danisbana.danisbanaapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.danisbana.danisbanaapp.presentation.screen.home.dashboard.DashboardRoute
import com.danisbana.danisbanaapp.presentation.screen.home.messages.MessagesRoute
import com.danisbana.danisbanaapp.presentation.screen.home.profile.ProfileRoute

@Composable
fun SetupHomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route
    ) {
        composable(route = Screen.Dashboard.route) { DashboardRoute() }
        composable(route = Screen.Messages.route) { MessagesRoute() }
        composable(route = Screen.Profile.route) { ProfileRoute() }
    }
}