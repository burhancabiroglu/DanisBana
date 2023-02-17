package com.danisbana.danisbanaapp.presentation.screen.home.conversation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.danisbana.danisbanaapp.core.model.MessageModel

@Composable
fun AuthorAndTextMessage(
    msg: MessageModel,
    isUserMe: Boolean,
    isFirstMessageByAuthor: Boolean,
    isLastMessageByAuthor: Boolean,
    authorClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        if (isLastMessageByAuthor) {
            Row(modifier = Modifier.semantics(mergeDescendants = true) {}) {
                Text(
                    text = msg.author,
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier
                        .alignBy(LastBaseline)
                        .paddingFrom(LastBaseline, after = 8.dp)
                )
            }
        }
        ChatItemBubble(msg, isUserMe, authorClicked = authorClicked)
        if (isFirstMessageByAuthor) {
            Spacer(modifier = Modifier.height(8.dp))
        } else {
            // Between bubbles
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}