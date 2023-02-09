package com.danisbana.danisbanaapp.presentation.screen.home.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.danisbana.danisbanaapp.presentation.components.MAppBar
import com.danisbana.danisbanaapp.presentation.screen.home.dashboard.components.ConsultantCard
import com.danisbana.danisbanaapp.presentation.screen.home.dashboard.components.LatestActivityCard

@Composable
fun DashboardScreen(
    state: DashboardState = DashboardState(),
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val scrollableState = rememberScrollState()
    Scaffold(
       topBar = {
           MAppBar(
               title = "Ana Sayfa"
           )
       }
   ) {
       Column(Modifier.padding(it).verticalScroll(scrollableState)) {
           ConsultantCard()
           LatestActivityCard()
       }
   }
}

@Composable
@Preview(name = "Dashboard")
private fun DashboardScreenPreview() {
    DashboardScreen()
}

