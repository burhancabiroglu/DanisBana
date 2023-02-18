package com.danisbana.danisbanaapp.presentation.screen.home.conversation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.danisbana.danisbanaapp.core.model.message.MessageStatus
import com.danisbana.danisbanaapp.presentation.components.MAppBar
import com.danisbana.danisbanaapp.presentation.screen.home.conversation.components.MessageTile

@Composable
fun ConversationScreen(
    state: ConversationState = ConversationState(),
    actions: ConversationActions = ConversationActions(),
    navController: NavHostController = rememberNavController()
) {
    val scrollState = rememberLazyListState()
    val messages = state.messages
    val authorMe = "authorMe"

    return Surface {
        Box(modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()) {
            Column(Modifier.fillMaxSize()){
                MAppBar(
                    title = state.channelName,
                    messageStatus = MessageStatus.ANSWERED,
                    navHostController = navController
                )
                LazyColumn(
                    reverseLayout = false,
                    state = scrollState,
                    modifier = Modifier.wrapContentHeight()
                ) {
                    for (index in messages.indices) {
                        val prevAuthor = messages.getOrNull(index - 1)?.author
                        val nextAuthor = messages.getOrNull(index + 1)?.author
                        val content = messages[index]
                        val isFirstMessageByAuthor = prevAuthor != content.author
                        val isLastMessageByAuthor = nextAuthor != content.author

                        item {
                            MessageTile(
                                onAuthorClick = { },
                                msg = content,
                                isUserMe = content.author == authorMe,
                                isFirstMessageByAuthor = isFirstMessageByAuthor,
                                isLastMessageByAuthor = isLastMessageByAuthor
                            )
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
    MaterialTheme {
        ConversationScreen()
    }
}

