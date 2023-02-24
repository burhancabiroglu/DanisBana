package com.danisbana.danisbanaapp.presentation.screen.home.messages.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danisbana.danisbanaapp.core.model.message.MessageEntity
import com.danisbana.danisbanaapp.core.model.message.MessageStatus
import com.danisbana.danisbanaapp.presentation.theme.AppDimens
import com.danisbana.danisbanaapp.presentation.theme.DanisBanaAppTheme
import com.danisbana.danisbanaapp.presentation.theme.White

@Composable
fun MessageListItem(
    modifier: Modifier = Modifier,
    item: MessageEntity = MessageEntity(),
    dividerVisibility: Boolean = true
) {
    Box(modifier.background(White)) {
        Column(
            modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.body2,
                    color = Color(0xFF343a40)
                )
                Text(
                    text = item.dateString,
                    style = MaterialTheme.typography.body1
                )
            }
            Spacer(modifier = modifier.height(5.dp))
            Text(
                text = item.content,
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = modifier.height(8.dp))
            StatusPointerCircle(status = item.status)
            Spacer(modifier = modifier.height(5.dp))
            if(dividerVisibility) Divider()
            Spacer(modifier = modifier.height(2.dp))
        }
    }
}

@Composable
fun StatusPointerCircle(
    modifier: Modifier = Modifier,
    status: MessageStatus
) {
    Row(
        modifier =
        modifier
            .wrapContentHeight()
            .wrapContentWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier
                .size(AppDimens.s8dp)
                .background(status.color, CircleShape)
        )
        Spacer(modifier = Modifier.size(5.dp))
        Text(
            text = status.label,
            style = MaterialTheme.typography.overline,
            color = Color(0xFF6c757d)
        )
    }
}


@Preview(name = "MessageListItem")
@Composable
private fun PreviewMessageListItem() {
    DanisBanaAppTheme {
        MessageListItem()
    }
}