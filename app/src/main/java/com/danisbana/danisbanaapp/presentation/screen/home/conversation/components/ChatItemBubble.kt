package com.danisbana.danisbanaapp.presentation.screen.home.conversation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.danisbana.danisbanaapp.core.model.message.MessageModel
import com.danisbana.danisbanaapp.core.util.messageFormatter
import com.danisbana.danisbanaapp.presentation.theme.*

@Composable
fun ChatItemBubble(
    message: MessageModel,
    isUserMe: Boolean,
    authorClicked: (String) -> Unit
) {
    val textColor = if (isUserMe) White else  LocalContentColor.current
    val backgroundBubbleColor = if (isUserMe) {
        QueenBlue
    } else {
        QueenBlue.copy(alpha = 0.15f)
    }

    Column {
        Surface(
            color = backgroundBubbleColor,
            shape = ChatBubbleShape
        ) {
            Text(
                text = messageFormatter(text = message.content, primary = isUserMe),
                style = MaterialTheme.typography.body2.copy(color = textColor),
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}