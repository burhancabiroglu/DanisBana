package com.danisbana.danisbanaapp.presentation.screen.admin.pool

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.danisbana.danisbanaapp.presentation.theme.DanisBanaAppTheme

@Composable
fun MessagePoolScreen(
    state: MessagePoolState = MessagePoolState(),
    actions: MessagePoolActions = MessagePoolActions()
) {
    // TODO UI Logic
}

@Composable
@Preview(name = "MessagePool")
private fun MessagePoolScreenPreview() {
    DanisBanaAppTheme {
        MessagePoolScreen()
    }
}

