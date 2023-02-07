package com.danisbana.danisbanaapp.presentation.screen.home.root

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.danisbana.danisbanaapp.presentation.components.bottomnav.MBottomNavigationBar
import com.danisbana.danisbanaapp.presentation.components.bottomnav.NavItemObj
import com.danisbana.danisbanaapp.presentation.navigation.SetupHomeNavGraph
import com.danisbana.danisbanaapp.presentation.theme.White

@Composable
fun HomeScreen(
    state: HomeState = HomeState(),
    actions: HomeActions = HomeActions(),
    navController: NavHostController = rememberNavController()
) {
    (LocalContext.current as? Activity?)?.let {
        WindowCompat.setDecorFitsSystemWindows(it.window, true)
    }
    val bottomNavItems = remember { NavItemObj.generate() }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        content = {
            it.calculateBottomPadding()
            SetupHomeNavGraph(navController = navController)
        },
        bottomBar = {
            MBottomNavigationBar(
                items = bottomNavItems,
                onItemCLick = navController::navigate
            )
        }
    )

}

@Composable
@Preview(name = "Home")
private fun HomeScreenPreview() {
    HomeScreen()
}

