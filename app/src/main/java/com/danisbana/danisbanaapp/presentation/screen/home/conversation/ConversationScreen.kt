package com.danisbana.danisbanaapp.presentation.screen.home.conversation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.danisbana.danisbanaapp.core.model.MessageModel
import com.danisbana.danisbanaapp.presentation.components.UserInput
import com.danisbana.danisbanaapp.presentation.screen.home.conversation.components.MessagesColumn
import kotlinx.coroutines.launch

@Composable
fun ConversationScreen(
    state: ConversationState = ConversationState(
        channelMembers = 2,
        channelName = "deneme",
        initialMessages = _initialMessages
    ),
    actions: ConversationActions = ConversationActions()
) {
    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    return Surface {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                Modifier.fillMaxSize()
            ){
                MessagesColumn(
                    messages = state.messages,
                    navigateToProfile = {},
                    modifier = Modifier.weight(1f),
                    scrollState = scrollState
                )
                UserInput(
                    onMessageSent = { content ->
                        state.addMessage(
                            MessageModel("authorMe", content, "timeNow")
                        )
                    },
                    resetScroll = {
                        scope.launch { scrollState.scrollToItem(0) }
                    },
                    modifier = Modifier.navigationBarsPadding().imePadding(),
                )
            }
        }
    }
}

@Composable
@Preview(name = "Conversation")
private fun ConversationScreenPreview() {
    MaterialTheme {
        ConversationScreen()
    }
}

