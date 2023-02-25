package com.danisbana.danisbanaapp.presentation.screen.home.messages

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.danisbana.danisbanaapp.presentation.screen.home.conversation.ConversationActivity
import com.danisbana.danisbanaapp.presentation.screen.home.conversation.ConversationArgs
import com.danisbana.danisbanaapp.presentation.screen.home.root.HomeActions

@Composable
fun MessagesRoute(
    viewModel: MessagesViewModel = hiltViewModel(),
    homeActions: HomeActions = HomeActions()
) {
    val context = LocalContext.current
    val uiState by viewModel.stateFlow.collectAsState(MessagesState())
    val actions = rememberMessagesActions(viewModel)
    actions.routeConsultant = homeActions.routeConsultant

    actions.routeConversation = {
        ConversationActivity.launch(
            context,
            ConversationArgs(it)
        )
    }
    MessagesScreen(uiState, actions)

    LaunchedEffect(true) {
       viewModel.getData()
    }


}


@Composable
fun rememberMessagesActions(viewModel: MessagesViewModel): MessagesActions {
    return remember(viewModel) {
        MessagesActions()
    }
}