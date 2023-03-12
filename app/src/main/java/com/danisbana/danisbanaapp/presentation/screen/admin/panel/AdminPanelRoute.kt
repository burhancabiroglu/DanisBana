package com.danisbana.danisbanaapp.presentation.screen.admin.panel

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.danisbana.danisbanaapp.presentation.screen.admin.conversation.AdminConversationActivity
import com.danisbana.danisbanaapp.presentation.screen.admin.conversation.AdminConversationArgs

@Composable
fun AdminPanelRoute(
    viewModel: AdminPanelViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.stateFlow.collectAsState()
    val actions = rememberAdminPanelActions(viewModel)

    val activityLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) {
        viewModel.getData()
        uiState.tabBarState.selectedIndex = 0
    }


    actions.routeConversation = {
        AdminConversationActivity.launch(
            activityLauncher,
            context,
            AdminConversationArgs(it.id)
        )
    }

    AdminPanelScreen(uiState, actions)

    DisposableEffect(true) {
        viewModel.getData()
        onDispose {  }
    }
}


@Composable
fun rememberAdminPanelActions(viewModel: AdminPanelViewModel): AdminPanelActions {
    return remember(viewModel) {
        AdminPanelActions()
    }
}