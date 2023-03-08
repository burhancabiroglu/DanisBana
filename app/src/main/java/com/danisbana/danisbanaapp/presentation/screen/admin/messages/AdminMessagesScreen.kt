package com.danisbana.danisbanaapp.presentation.screen.admin.messages

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.danisbana.danisbanaapp.presentation.theme.DanisBanaAppTheme

@Composable
fun AdminMessagesScreen(
    state: AdminMessagesState = AdminMessagesState(),
    actions: AdminMessagesActions = AdminMessagesActions()
) {
    // TODO UI Logic
}

@Composable
@Preview(name = "AdminMessages")
private fun AdminMessagesScreenPreview() {
    DanisBanaAppTheme {
        AdminMessagesScreen()
    }
}

