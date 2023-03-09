package com.danisbana.danisbanaapp.presentation.screen.admin.conversation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.danisbana.danisbanaapp.presentation.theme.DanisBanaAppTheme

@Composable
fun AdminConversationScreen(
    state: AdminConversationState = AdminConversationState(),
    actions: AdminConversationActions = AdminConversationActions()
) {

}

@Preview
@Composable
fun AdminConversationScreenPreview() {
    DanisBanaAppTheme {
        AdminConversationScreen()
    }
}