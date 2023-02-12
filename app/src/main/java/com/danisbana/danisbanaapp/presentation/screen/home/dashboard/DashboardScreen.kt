package com.danisbana.danisbanaapp.presentation.screen.home.dashboard

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import com.danisbana.danisbanaapp.R
import com.danisbana.danisbanaapp.presentation.components.MAppBar
import com.danisbana.danisbanaapp.presentation.screen.home.dashboard.components.ConsultantCard
import com.danisbana.danisbanaapp.presentation.screen.home.dashboard.components.LatestActivityCard
import com.danisbana.danisbanaapp.presentation.screen.home.root.HomeViewModel
import com.danisbana.danisbanaapp.presentation.theme.DanisBanaAppTheme

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel(),
    sharedViewModel: HomeViewModel? = null
) {
    val uiState by viewModel.stateFlow.collectAsState(DashboardState())
    val scrollableState = rememberScrollState()
    Scaffold(
       topBar = {
           MAppBar(title = stringResource(R.string.home_page))
       }
   ) {
       Column(
           Modifier
               .padding(it)
               .verticalScroll(scrollableState)) {
           ConsultantCard(
               onClick = {
                   sharedViewModel?.routeConsultant()
               }
           )
           LatestActivityCard()
       }
   }
}

@Composable
@Preview(name = "Dashboard")
private fun DashboardScreenPreview() {
    val viewModel = remember {
        DashboardViewModel(SavedStateHandle())
    }
    DanisBanaAppTheme {
        DashboardScreen(viewModel)
    }
}

