package com.danisbana.danisbanaapp.presentation.screen.home.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProfileScreen(
    state: ProfileState = ProfileState(),
    actions: ProfileActions = ProfileActions()
) {
    // TODO UI Logic
}

@Composable
@Preview(name = "Profile")
private fun ProfileScreenPreview() {
    ProfileScreen()
}

