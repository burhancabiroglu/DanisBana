package com.danisbana.danisbanaapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.danisbana.danisbanaapp.presentation.screen.admin.messages.AdminMessagesRoute
import com.danisbana.danisbanaapp.presentation.screen.admin.pool.MessagePoolRoute

@Composable
fun AdminNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.AdminMessages.route
    ) {
        composable(route = Screen.AdminMessages.route){
            AdminMessagesRoute()
        }
        composable(route = Screen.MessagePool.route){
            MessagePoolRoute()
        }
    }
}