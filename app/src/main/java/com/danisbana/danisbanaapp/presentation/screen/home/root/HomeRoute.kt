package com.danisbana.danisbanaapp.presentation.screen.home.root

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import com.danisbana.danisbanaapp.presentation.navigation.Screen

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.stateFlow.collectAsState(HomeState())
    val actions = rememberHomeActions(viewModel)
    actions.routeConsultant = { navController.navigate(Screen.Consultant.route) }
    actions.routeLogin = {
        navController.popBackStack()
        navController.navigate(Screen.Login.route)
    }
    actions.routeConversation = {
        navController.navigate(Screen.Conversation.route.plus("/$it"))
    }

    HomeScreen(uiState, actions)


    DisposableEffect(true) {
        viewModel.onRecompose()
        onDispose {
            viewModel.onDispose()
        }
    }
}


@Composable
fun rememberHomeActions(viewModel: HomeViewModel): HomeActions {
    return remember(viewModel) {
        HomeActions()
    }
}