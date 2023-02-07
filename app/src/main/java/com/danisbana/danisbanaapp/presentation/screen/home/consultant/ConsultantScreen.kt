package com.danisbana.danisbanaapp.presentation.screen.home.consultant

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.danisbana.danisbanaapp.presentation.theme.White

@Composable
fun ConsultantScreen(
    state: ConsultantState = ConsultantState(),
    actions: ConsultantActions = ConsultantActions()
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ){
        Text(text = "ConsultantRoute")
    }

}

@Composable
@Preview(name = "Consultant")
private fun ConsultantScreenPreview() {
    ConsultantScreen()
}

