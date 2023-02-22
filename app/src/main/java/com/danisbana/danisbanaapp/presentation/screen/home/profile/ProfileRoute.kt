package com.danisbana.danisbanaapp.presentation.screen.home.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.danisbana.danisbanaapp.presentation.screen.home.root.HomeViewModel

@Composable
fun ProfileRoute(
    viewModel: ProfileViewModel = hiltViewModel(),
    sharedViewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.stateFlow.collectAsState(ProfileState())
    val actions = rememberProfileActions(viewModel)
    actions.logout = {
        viewModel.logout()
        sharedViewModel.logout()
    }
    ProfileScreen(uiState, actions)
}


@Composable
fun rememberProfileActions(viewModel: ProfileViewModel): ProfileActions {
    return remember(viewModel) {
        ProfileActions(
            logout = viewModel::logout
        )
    }
}