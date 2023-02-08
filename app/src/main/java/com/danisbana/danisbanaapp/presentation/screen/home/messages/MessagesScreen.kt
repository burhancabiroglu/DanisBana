package com.danisbana.danisbanaapp.presentation.screen.home.messages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.danisbana.danisbanaapp.presentation.components.MAppBar
import com.danisbana.danisbanaapp.presentation.screen.home.messages.components.MessageListItem
import com.danisbana.danisbanaapp.presentation.theme.White

@Composable
fun MessagesScreen(
    state: MessagesState = MessagesState(),
    viewModel: MessagesViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            MAppBar(
                title = "Mesajlarım"
            )
        }
    ) {
        Box(
            modifier = Modifier
                .background(White)
                .padding(it)){

            LazyColumn {
                for (i in 0..10){
                    item {
                        MessageListItem()
                    }
                }
            }
        }
    }
}

@Composable
@Preview(name = "Messages")
private fun MessagesScreenPreview() {
    MessagesScreen()
}

