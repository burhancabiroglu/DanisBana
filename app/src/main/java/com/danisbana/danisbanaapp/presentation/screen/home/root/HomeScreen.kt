package com.danisbana.danisbanaapp.presentation.screen.home.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.danisbana.danisbanaapp.presentation.components.bottomnav.MBottomNavigationBar
import com.danisbana.danisbanaapp.presentation.components.bottomnav.getBottomNavigationItems
import com.danisbana.danisbanaapp.presentation.navigation.SetupHomeNavGraph
import com.danisbana.danisbanaapp.presentation.theme.White

@Composable
fun HomeScreen(
    state: HomeState = HomeState(),
    actions: HomeActions = HomeActions(),
) {
    val bottomNavController = rememberNavController()
    val bottomNavItems = getBottomNavigationItems()


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding(),
        backgroundColor = White,
        content = {
            it.calculateBottomPadding()
            SetupHomeNavGraph(
                navController = bottomNavController,
                actions
            )
        },
        bottomBar = {
            MBottomNavigationBar(
                state = state.bottomNavState,
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

