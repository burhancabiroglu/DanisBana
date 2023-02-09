package com.danisbana.danisbanaapp.presentation.screen.home.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.danisbana.danisbanaapp.presentation.components.MAppBar

@Composable
fun DashboardScreen(
    state: DashboardState = DashboardState(),
    viewModel: DashboardViewModel = hiltViewModel()
) {
   Scaffold(
       topBar = {
           MAppBar(
               title = "Ana Sayfa"
           )
       }
   ) {
       Box(Modifier.padding(it)) {
           
       }
   }
}

@Composable
@Preview(name = "Dashboard")
private fun DashboardScreenPreview() {
    DashboardScreen()
}

