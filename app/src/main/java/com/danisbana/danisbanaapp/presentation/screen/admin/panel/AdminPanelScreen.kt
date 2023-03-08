package com.danisbana.danisbanaapp.presentation.screen.admin.panel

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.danisbana.danisbanaapp.presentation.navigation.AdminNavGraph
import com.danisbana.danisbanaapp.presentation.theme.DanisBanaAppTheme

@Composable
fun AdminPanelScreen(
    state: AdminPanelState = AdminPanelState(),
    actions: AdminPanelActions = AdminPanelActions(),
    navHostController: NavHostController = rememberNavController()
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        AdminNavGraph(
            navHostController = navHostController
        )
    }
}

@Composable
@Preview(name = "AdminPanel")
private fun AdminPanelScreenPreview() {
    DanisBanaAppTheme {
        AdminPanelScreen()
    }
}

