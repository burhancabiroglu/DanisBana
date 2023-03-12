package com.danisbana.danisbanaapp.presentation.screen.admin.pool

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.danisbana.danisbanaapp.domain.base.BaseScaffold
import com.danisbana.danisbanaapp.presentation.screen.admin.panel.AdminPanelActions
import com.danisbana.danisbanaapp.presentation.screen.admin.panel.AdminPanelState
import com.danisbana.danisbanaapp.presentation.screen.home.messages.components.MessageListItem
import com.danisbana.danisbanaapp.presentation.theme.DanisBanaAppTheme
import com.danisbana.danisbanaapp.presentation.theme.White

@Composable
fun MessagePoolScreen(
    state: AdminPanelState = AdminPanelState(),
    actions: AdminPanelActions = AdminPanelActions()
) {
    BaseScaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .statusBarsPadding(),
        dialogState = state.dialogState,
        snackBarHostState = state.snackBarHostState,
        loadingState = state.pageLoading,
    ) {
        Box(modifier = Modifier
            .background(White)
            .fillMaxSize()
        ){
            LazyColumn {
                for (i in state.poolMessages){
                    item {
                        MessageListItem(
                            item = i,
                            touchAction = { actions.routeConversation(i) },
                            deleteAction = {}
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(name = "MessagePool")
private fun MessagePoolScreenPreview() {
    DanisBanaAppTheme {
        MessagePoolScreen()
    }
}

