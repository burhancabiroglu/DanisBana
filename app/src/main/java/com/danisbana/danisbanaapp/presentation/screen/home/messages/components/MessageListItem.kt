package com.danisbana.danisbanaapp.presentation.screen.home.messages.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.danisbana.danisbanaapp.core.model.message.MessageEntity
import com.danisbana.danisbanaapp.core.model.message.MessageStatus
import com.danisbana.danisbanaapp.presentation.theme.AppDimens
import com.danisbana.danisbanaapp.presentation.theme.DanisBanaAppTheme
import com.danisbana.danisbanaapp.presentation.theme.Red
import com.danisbana.danisbanaapp.presentation.theme.White
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MessageListItem(
    modifier: Modifier = Modifier,
    item: MessageEntity = MessageEntity(),
    dividerVisibility: Boolean = true,
    touchAction: () -> Unit = {},
    deleteAction: (id:String) -> Unit = {}
) {
    val density = LocalDensity.current.density
    val width = LocalConfiguration.current.screenWidthDp
    val swipeState = rememberSwipeableState(initialValue = 0)
    val swipeValue = 180f

    Box(
        modifier
            .background(White)
            .swipeable(
                swipeState,
                anchors = mapOf(0f to 0, -swipeValue to 1),
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal,
                resistance = ResistanceConfig(
                    basis = width/density,
                )
            )
            .height(IntrinsicSize.Min)
    ) {
        Box(
            modifier = Modifier
                .width((swipeValue / density).dp)
                .offset {
                    IntOffset(swipeState.offset.value.roundToInt() + swipeValue.roundToInt(), 0)
                }
                .padding(bottom = 12.dp)
                .fillMaxHeight()
                .align(Alignment.CenterEnd)
                .background(Red.copy(alpha = 0.5f))
                .clickable {
                    deleteAction.invoke(item.id)
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(26.dp),
                imageVector = Icons.Default.DeleteOutline,
                tint = White,
                contentDescription = ""
            )
        }
        Box(
            modifier =
            Modifier
                .offset {
                    IntOffset(swipeState.offset.value.roundToInt(), 0)
                }
                .clickable { touchAction.invoke() }

        ) {
            Column(
                modifier = modifier.padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
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
                    style = MaterialTheme.typography.body1,
                    maxLines = 2,
                    softWrap = true,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = modifier.height(8.dp))
                StatusPointerCircle(status = item.status)
                Spacer(modifier = modifier.height(5.dp))
                if (dividerVisibility) Divider()
                Spacer(modifier = modifier.height(2.dp))
            }
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