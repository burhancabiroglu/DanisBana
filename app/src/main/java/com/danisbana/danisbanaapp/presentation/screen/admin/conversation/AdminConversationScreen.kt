package com.danisbana.danisbanaapp.presentation.screen.admin.conversation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danisbana.danisbanaapp.core.model.message.MessageStatus
import com.danisbana.danisbanaapp.domain.base.BaseScaffold
import com.danisbana.danisbanaapp.presentation.components.MAppBar
import com.danisbana.danisbanaapp.presentation.components.UserInput
import com.danisbana.danisbanaapp.presentation.screen.admin.conversation.components.MessageStatusUpdateInput
import com.danisbana.danisbanaapp.presentation.screen.home.conversation.components.ChatItemBubble
import com.danisbana.danisbanaapp.presentation.screen.home.messages.components.StatusPointerCircle
import com.danisbana.danisbanaapp.presentation.theme.AppDimens
import com.danisbana.danisbanaapp.presentation.theme.DanisBanaAppTheme

@Composable
fun AdminConversationScreen(
    state: AdminConversationState = AdminConversationState(),
    actions: AdminConversationActions = AdminConversationActions()
) {
    val scrollState = rememberLazyListState()
    val message = state.message
    BaseScaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .statusBarsPadding(),
        loadingState = state.loadingState,
        topBar = {
            MAppBar(
                title = state.channelName,
                subTitle = { message?.status?.let{ StatusPointerCircle(status = it) } },
                onBackAction = actions.onBackClick
            )
        }
    ) {
        message?.let {
            Box {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = AppDimens.wallSpace, vertical = 22.dp)
                    ) {
                        LazyColumn(
                            state = scrollState,
                            modifier = Modifier.wrapContentHeight()
                        ) {
                            item {
                                ChatItemBubble(
                                    message = message,
                                    isUserMe = false,
                                    isEditor = true
                                )
                            }
                            message.answer?.let {
                                item {
                                    ChatItemBubble(
                                        message = it,
                                        isUserMe = true,
                                        isEditor = true
                                    )
                                }
                            }

                        }
                    }
                    if(message.status === MessageStatus.PENDING) {
                        MessageStatusUpdateInput(
                            modifier = Modifier
                                .navigationBarsPadding()
                                .imePadding(),
                            rejectAction = actions.rejectAction,
                            confirmAction =  actions.confirmAction
                        )
                    }
                    if (message.status === MessageStatus.ACCEPTED) {
                        UserInput(
                            onMessageSent = actions.onMessageSent,
                            modifier = Modifier
                                .navigationBarsPadding()
                                .imePadding()
                        )
                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun AdminConversationScreenPreview() {
    DanisBanaAppTheme {
        AdminConversationScreen()
    }
}