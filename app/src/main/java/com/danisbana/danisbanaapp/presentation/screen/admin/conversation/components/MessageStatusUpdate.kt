package com.danisbana.danisbanaapp.presentation.screen.admin.conversation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danisbana.danisbanaapp.core.model.message.MessageStatus
import com.danisbana.danisbanaapp.presentation.theme.DanisBanaAppTheme
import com.danisbana.danisbanaapp.presentation.theme.Red

@Composable
fun MessageStatusUpdateInput(
    modifier: Modifier = Modifier,
    confirmAction: () -> Unit = {},
    rejectAction: () -> Unit = {},
) {
    val borderColor: Color = MaterialTheme.colors.onSurface.copy(alpha =  0.12f)

    return Surface {
        Column(
            modifier = modifier.height(180.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp,Alignment.Top)
        ) {
            Divider()
            Text(text = "Mesajı cevaplamadan önce\nlütfen bir seçenek seçiniz?")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(24.dp,Alignment.CenterHorizontally)
            ) {
                Button(
                    onClick = rejectAction,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0x08232531),
                    ),
                    border = BorderStroke(1.dp,borderColor),
                    elevation = ButtonDefaults.elevation(0.dp,0.dp)
                ) {
                    Text(
                        text = "Reddet",
                        style = MaterialTheme.typography.subtitle2,
                        color = Color(0x4D232531)
                    )
                }
                Button(
                    onClick = confirmAction,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0x08232531),
                    ),
                    border = BorderStroke(1.dp,borderColor),
                    elevation = ButtonDefaults.elevation(0.dp,0.dp)
                ) {
                    Text(
                        text = "Onayla",
                        style = MaterialTheme.typography.subtitle2,
                        color = MessageStatus.ACCEPTED.color
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun MessageStatusUpdateInputPreview() {
    DanisBanaAppTheme {
        MessageStatusUpdateInput()
    }
}

