package com.danisbana.danisbanaapp.presentation.screen.home.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ProfileRoute(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.stateFlow.collectAsState(ProfileState())
    val actions = rememberProfileActions(viewModel)
    ProfileScreen(uiState, actions)
}


@Composable
fun rememberProfileActions(viewModel: ProfileViewModel): ProfileActions {
    return remember(viewModel) {
        ProfileActions()
    }
}