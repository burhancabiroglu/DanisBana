package com.danisbana.danisbanaapp.presentation.screen.home.messages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.danisbana.danisbanaapp.R
import com.danisbana.danisbanaapp.core.model.message.sampleItems
import com.danisbana.danisbanaapp.presentation.components.MAppBar
import com.danisbana.danisbanaapp.presentation.screen.home.messages.components.MessageListItem
import com.danisbana.danisbanaapp.presentation.theme.QueenBlue
import com.danisbana.danisbanaapp.presentation.theme.White

@Composable
fun MessagesScreen(
    state: MessagesState = MessagesState(),
    viewModel: MessagesViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = { MAppBar(title = stringResource(id = R.string.messages)) },
    ) {
        Box(modifier = Modifier
            .background(White)
            .padding(it)
            .fillMaxSize()
        ){
            LazyColumn {
                for (i in sampleItems){
                    item { MessageListItem(item = i) }
                }
            }
            FloatingActionButton(
                modifier =  Modifier
                    .size(56.dp)
                    .align(alignment = Alignment.BottomEnd)
                    .offset(y = (-100).dp, x = (-10).dp),
                elevation = FloatingActionButtonDefaults.elevation(10.dp),
                onClick = {},
                backgroundColor = QueenBlue,
                contentColor = White,
                content = {
                    Icon(
                        imageVector = Icons.Default.Edit ,
                        contentDescription = "",
                    )
                }
            )
        }
    }
}

@Composable
@Preview(name = "Messages")
private fun MessagesScreenPreview() {
    MessagesScreen()
}

