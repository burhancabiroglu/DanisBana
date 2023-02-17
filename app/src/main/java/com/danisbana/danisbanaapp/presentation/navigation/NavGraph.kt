package com.danisbana.danisbanaapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.danisbana.danisbanaapp.presentation.screen.auth.login.LoginScreen
import com.danisbana.danisbanaapp.presentation.screen.auth.register.RegisterScreen
import com.danisbana.danisbanaapp.presentation.screen.home.consultant.ConsultantScreen
import com.danisbana.danisbanaapp.presentation.screen.home.conversation.ConversationRoute
import com.danisbana.danisbanaapp.presentation.screen.home.conversation.ConversationScreen
import com.danisbana.danisbanaapp.presentation.screen.home.root.HomeScreen
import com.danisbana.danisbanaapp.presentation.screen.splash.SplashRoute

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route){
            SplashRoute(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.Register.route) {
            RegisterScreen(navController = navController,)
        }
        composable(route = Screen.Consultant.route){
            ConsultantScreen(navController = navController)
        }
        composable(route = Screen.Conversation.route){
            ConversationRoute(navController = navController)
        }
    }
}