package com.danisbana.danisbanaapp.presentation.screen.home.conversation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danisbana.danisbanaapp.domain.base.BaseScaffold
import com.danisbana.danisbanaapp.presentation.components.MAppBar
import com.danisbana.danisbanaapp.presentation.screen.home.conversation.components.ChatItemBubble
import com.danisbana.danisbanaapp.presentation.screen.home.messages.components.StatusPointerCircle
import com.danisbana.danisbanaapp.presentation.theme.AppDimens
import com.danisbana.danisbanaapp.presentation.theme.DanisBanaAppTheme

@Composable
fun ConversationScreen(
    state: ConversationState = ConversationState(),
    actions: ConversationActions = ConversationActions(),
) {
    val scrollState = rememberLazyListState()
    val message = state.message
    val answer = state.message?.answer

    return BaseScaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .statusBarsPadding(),
        loadingState = state.loadingState,
        topBar = {
            MAppBar(
                title = state.channelName,
                subTitle = { if (message!=null) StatusPointerCircle(status = message.status) },
                onBackAction = actions.onBackClick
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(horizontal = AppDimens.wallSpace, vertical = 22.dp)) {
            if(message != null) {
                LazyColumn(
                    state = scrollState,
                    modifier = Modifier.wrapContentHeight()
                ) {
                    item {
                        ChatItemBubble(message, true)
                    }
                    if(answer != null) {
                        item {
                            ChatItemBubble(answer, false)
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(name = "Conversation")
private fun ConversationScreenPreview() {
    DanisBanaAppTheme {
        ConversationScreen()
    }
}

