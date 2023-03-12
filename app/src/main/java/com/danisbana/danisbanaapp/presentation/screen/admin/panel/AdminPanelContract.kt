package com.danisbana.danisbanaapp.presentation.screen.admin.panel

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.danisbana.danisbanaapp.core.model.message.MessageEntity
import com.danisbana.danisbanaapp.domain.base.BaseDialogState
import com.danisbana.danisbanaapp.domain.base.LoadingState
import com.danisbana.danisbanaapp.presentation.components.bottomnav.BottomNavState


class AdminPanelState(
    val snackBarHostState: SnackbarHostState = SnackbarHostState()
) {
    var pageLoading = LoadingState(false)
    var dialogState = BaseDialogState()
    var poolMessages by mutableStateOf<List<MessageEntity>>(listOf())
    var editorMessages by mutableStateOf<List<MessageEntity>>(listOf())
    var tabBarState = BottomNavState()
}

data class AdminPanelActions(
    val onClick: () -> Unit = {},
    var routeConversation: (MessageEntity) -> Unit = {},
)

