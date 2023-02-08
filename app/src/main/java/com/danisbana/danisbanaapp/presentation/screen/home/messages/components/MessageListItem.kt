package com.danisbana.danisbanaapp.presentation.screen.home.messages.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danisbana.danisbanaapp.core.model.MessageItemDto
import com.danisbana.danisbanaapp.core.model.MessageStatus
import com.danisbana.danisbanaapp.core.model.SampleMessageItemDto
import com.danisbana.danisbanaapp.presentation.theme.AppDimens
import com.danisbana.danisbanaapp.presentation.theme.QueenBlue
import com.danisbana.danisbanaapp.presentation.theme.White
import javax.annotation.meta.When

@Composable
fun MessageListItem(
    modifier: Modifier = Modifier,
    item: MessageItemDto = SampleMessageItemDto
) {
    Box(modifier.background(White)) {
        Column(modifier.fillMaxWidth().wrapContentHeight()) {
            Row(
                modifier
                    .fillMaxWidth()
                    .wrapContentHeight()) {
                Text(text = item.title)
                Text(text = item.latestDateString)
            }
            Text(text = item.shortMessage)
            StatusPointerCircle(status = item.status)
            Spacer(modifier = modifier.height(5.dp))
            Divider()
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
                .size(AppDimens.s6dp)
                .background(status.color, CircleShape)
        )
        Text(text = status.label)
    }
}

@Preview(name = "MessageListItem")
@Composable
private fun PreviewMessageListItem() {
    MessageListItem()
}