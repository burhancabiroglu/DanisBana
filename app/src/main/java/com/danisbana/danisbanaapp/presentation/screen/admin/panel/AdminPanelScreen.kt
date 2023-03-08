package com.danisbana.danisbanaapp.presentation.screen.admin.panel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.danisbana.danisbanaapp.presentation.components.MAppBar
import com.danisbana.danisbanaapp.presentation.components.tabbar.MTabBar
import com.danisbana.danisbanaapp.presentation.components.tabbar.rememberTabItems
import com.danisbana.danisbanaapp.presentation.navigation.AdminNavGraph
import com.danisbana.danisbanaapp.presentation.theme.DanisBanaAppTheme

@Composable
fun AdminPanelScreen(
    state: AdminPanelState = AdminPanelState(),
    actions: AdminPanelActions = AdminPanelActions(),
) {
    val tabs = rememberTabItems()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MAppBar(
                title = "Admin Paneli"
            )
        }
    ) { pd ->
        Column(
            modifier = Modifier.padding(pd)
        ) {
            MTabBar(items = tabs)
            AdminNavGraph(
                navHostController = rememberNavController()
            )
        }
    }
}

@Composable
@Preview(name = "AdminPanel")
private fun AdminPanelScreenPreview() {
    DanisBanaAppTheme {
        AdminPanelScreen()
    }
}

