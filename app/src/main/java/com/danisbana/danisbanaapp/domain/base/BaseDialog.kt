package com.danisbana.danisbanaapp.domain.base

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@Composable
fun BaseDialog(
    title: String = "Çıkış yap",
    description: String = "Çıkış yapmak istediğinize\nemin misiniz?",
    buttonConfirm: String = "Onayla",
    buttonCancel: String = "Vazgeç",
    dialogState: BaseDialogState = BaseDialogState(),
    action: () -> Unit = {},
) {
    if (dialogState.isShowing) {
        AlertDialog(
            onDismissRequest = {
                dialogState.isShowing = false
            },
            confirmButton = {
                TextButton(onClick = {
                    action.invoke()
                    dialogState.isShowing = false
                }) {
                    Text(text = buttonConfirm)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    dialogState.isShowing = false
                }) {
                    Text(text = buttonCancel)
                }
            },
            title = { Text(text = title) },
            text = { Text(text = description) },
            modifier = Modifier.fillMaxWidth().padding(32.dp),
            shape = RoundedCornerShape(5.dp),
            backgroundColor = Color.White,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        )
    }
}


class BaseDialogState {
    var isShowing: Boolean by mutableStateOf(false)

    fun open() {
        isShowing = true
    }

    fun hide() {
        isShowing = false
    }

}

@Composable
fun rememberDialogState() = remember { BaseDialogState() }

