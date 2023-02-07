package com.danisbana.danisbanaapp.presentation.screen.home.dashboard

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danisbana.danisbanaapp.presentation.theme.White

@Composable
fun DashboardScreen(
    state: DashboardState = DashboardState(),
    actions: DashboardActions = DashboardActions()
) {
    /*topBar = {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            backgroundColor = White,
            elevation = 3.dp
        ) {

        }
    }*/
}

@Composable
@Preview(name = "Dashboard")
private fun DashboardScreenPreview() {
    DashboardScreen()
}

