package com.danisbana.danisbanaapp.presentation.screen.home.conversation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danisbana.danisbanaapp.R
import com.danisbana.danisbanaapp.core.model.message.Answer
import com.danisbana.danisbanaapp.core.model.message.MessageEntity
import com.danisbana.danisbanaapp.core.util.messageFormatter
import com.danisbana.danisbanaapp.presentation.theme.*

@Composable
fun ChatItemBubble(
    message: MessageEntity,
    isUserMe: Boolean,
    isEditor: Boolean = false
) {

    val textColor: Color
    val backgroundBubbleColor: Color
    val bubbleShape: Shape
    val arrangement: Arrangement.Horizontal

    if(isUserMe) {
        textColor = White
        backgroundBubbleColor = QueenBlue
        bubbleShape = ChatBubbleShapeReversed
        arrangement = Arrangement.End
    } else {
        textColor = LocalContentColor.current
        backgroundBubbleColor = QueenBlue.copy(alpha = 0.15f)
        bubbleShape = ChatBubbleShape
        arrangement = Arrangement.Start
    }
    val sw = LocalConfiguration.current.screenWidthDp

    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        horizontalArrangement = arrangement
    ) {
        Surface(
            modifier = Modifier.wrapContentSize().widthIn(min = (sw/6).dp, max = (sw /1.3).dp)
            ,
            color = backgroundBubbleColor,
            shape = bubbleShape
        ) {
            Column(
                modifier = Modifier.padding(top = 10.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
                horizontalAlignment = Alignment.End
            ) {
                if(isUserMe.not()) {
                    Text(
                        text = if (isEditor.not()) "" else stringResource(R.string.anonymous_user),
                        style = MaterialTheme.typography.caption.copy(fontSize = 12.sp),
                        color = Color(0xFF00AFAD),
                        modifier = Modifier.align(Alignment.Start),
                    )
                }
                Text(
                    text = messageFormatter(text = message.content, primary = isUserMe),
                    style = MaterialTheme.typography.body2.copy(color = textColor),
                    modifier = Modifier.padding(top = 2.dp, bottom = 4.dp),
                )
                Text(
                    text = message.dateString(),
                    style = MaterialTheme.typography.body1.copy(fontSize = 11.sp),
                    color = textColor.copy(alpha = 0.8f),
                    textAlign = TextAlign.End
                )
            }
        }
    }
}


@Composable
fun ChatItemBubble(
    message: Answer,
    isUserMe: Boolean,
    isEditor: Boolean = false
) {

    val textColor: Color
    val backgroundBubbleColor: Color
    val bubbleShape: Shape
    val arrangement: Arrangement.Horizontal

    if(isUserMe) {
        textColor = White
        backgroundBubbleColor = QueenBlue
        bubbleShape = ChatBubbleShapeReversed
        arrangement = Arrangement.End
    } else {
        textColor = LocalContentColor.current
        backgroundBubbleColor = QueenBlue.copy(alpha = 0.15f)
        bubbleShape = ChatBubbleShape
        arrangement = Arrangement.Start
    }
    val sw = LocalConfiguration.current.screenWidthDp
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        horizontalArrangement = arrangement
    ) {
        Surface(
            modifier = Modifier.wrapContentSize().widthIn(min = (sw/6).dp, max = (sw /1.5).dp),
            color = backgroundBubbleColor,
            shape = bubbleShape
        ) {
            Column(
                modifier = Modifier.padding(top = 10.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
                horizontalAlignment = Alignment.End
            ) {
                if(isUserMe.not()) {
                    Text(
                        text = if (isEditor.not()) "" else stringResource(R.string.anonymous_user),
                        style = MaterialTheme.typography.caption.copy(fontSize = 12.sp),
                        color = Color(0xFF00AFAD),
                        modifier = Modifier.align(Alignment.Start),
                    )
                }
                Text(
                    text = messageFormatter(text = message.content, primary = isUserMe),
                    style = MaterialTheme.typography.body2.copy(color = textColor),
                    modifier = Modifier.padding(top = 2.dp, bottom = 4.dp),
                )
                Text(
                    text = message.dateString(),
                    style = MaterialTheme.typography.body1.copy(fontSize = 11.sp),
                    color = textColor.copy(alpha = 0.8f),
                    textAlign = TextAlign.End
                )
            }
        }
    }
}