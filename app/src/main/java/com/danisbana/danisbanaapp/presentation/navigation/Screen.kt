package com.danisbana.danisbanaapp.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen(route = "splash_screen")
    object Home : Screen(route = "home_screen")
    object Consultant: Screen(route = "consultant_screen")
    object Dashboard: Screen(route = "dashboard_screen")
    object Profile: Screen(route = "profile_screen")
}