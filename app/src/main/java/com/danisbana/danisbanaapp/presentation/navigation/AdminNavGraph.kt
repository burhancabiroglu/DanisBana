package com.danisbana.danisbanaapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.danisbana.danisbanaapp.presentation.screen.admin.messages.AdminMessagesScreen
import com.danisbana.danisbanaapp.presentation.screen.admin.panel.AdminPanelActions
import com.danisbana.danisbanaapp.presentation.screen.admin.panel.AdminPanelState
import com.danisbana.danisbanaapp.presentation.screen.admin.pool.MessagePoolScreen

@Composable
fun AdminNavGraph(
    sharedState: AdminPanelState,
    sharedActions: AdminPanelActions,
    navHostController: NavHostController,
    startDestination:Screen = Screen.AdminMessages
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination.route
    ) {
        composable(route = Screen.AdminMessages.route){
            AdminMessagesScreen(
                sharedState,
                sharedActions
            )
        }
        composable(route = Screen.MessagePool.route){
            MessagePoolScreen(
                sharedState,
                sharedActions
            )
        }
    }
}