package com.danisbana.danisbanaapp.presentation.screen.home.dashboard.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ConsultantCard(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "ConsultantCard")
    }
}

@Preview(name = "ConsultantCard")
@Composable
private fun PreviewConsultantCard() {
    ConsultantCard()
}