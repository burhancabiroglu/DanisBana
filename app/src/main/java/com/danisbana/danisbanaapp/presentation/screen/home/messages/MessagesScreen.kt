package com.danisbana.danisbanaapp.presentation.screen.home.messages

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.danisbana.danisbanaapp.domain.base.BaseScaffold
import com.danisbana.danisbanaapp.presentation.components.MAppBar
import com.danisbana.danisbanaapp.presentation.screen.home.messages.components.MessageListItem
import com.danisbana.danisbanaapp.presentation.theme.DanisBanaAppTheme
import com.danisbana.danisbanaapp.presentation.theme.QueenBlue
import com.danisbana.danisbanaapp.presentation.theme.White

@Composable
fun MessagesScreen(
    state: MessagesState = MessagesState(),
    actions: MessagesActions = MessagesActions()
) {
    BaseScaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .statusBarsPadding(),
        topBar = { MAppBar(title = stringResource(id = R.string.messages)) },
        loadingState = state.pageLoading,
        snackBarHostState = state.snackBarHostState
    ) {
        Box(modifier = Modifier
            .background(White)
            .fillMaxSize()
        ){
            LazyColumn {
                for (i in state.messages){
                    item {
                        MessageListItem(
                            item = i,
                            modifier = Modifier.clickable {
                                actions.routeConversation.invoke(i)
                            }
                        )
                    }
                }
            }
            FloatingActionButton(
                modifier = Modifier
                    .size(56.dp)
                    .align(alignment = Alignment.BottomEnd)
                    .offset(y = (-100).dp, x = (-10).dp),
                elevation = FloatingActionButtonDefaults.elevation(10.dp),
                onClick = actions.routeConsultant,
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
   DanisBanaAppTheme {
       MessagesScreen()
   }
}

