package com.danisbana.danisbanaapp.presentation.screen.home.root

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.danisbana.danisbanaapp.presentation.components.bottomnav.MBottomNavigationBar
import com.danisbana.danisbanaapp.presentation.components.bottomnav.NavItemObj
import com.danisbana.danisbanaapp.presentation.navigation.Screen
import com.danisbana.danisbanaapp.presentation.navigation.SetupHomeNavGraph
import com.danisbana.danisbanaapp.presentation.theme.White
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    //val uiState by viewModel.stateFlow.collectAsState(HomeState())
    val bottomNavController = rememberNavController()
    val bottomNavItems = remember { NavItemObj.generate() }

    LaunchedEffect(true){
        viewModel.navChannel.collectLatest {
            when(it) {
                is HomeNavChannel.RouteConsultant ->
                    navController.navigate(Screen.Conversation.route)
                is HomeNavChannel.RouteLogin -> {
                    navController.popBackStack()
                    navController.navigate(Screen.Login.route)
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding()
            .background(White),
        content = {
            it.calculateBottomPadding()
            SetupHomeNavGraph(navController = bottomNavController,viewModel)
        },
        bottomBar = {
            MBottomNavigationBar(
                items = bottomNavItems,
                onItemCLick = bottomNavController::navigate
            )
        }
    )

}

@Composable
@Preview(name = "Home")
private fun HomeScreenPreview() {
    HomeScreen()
}

