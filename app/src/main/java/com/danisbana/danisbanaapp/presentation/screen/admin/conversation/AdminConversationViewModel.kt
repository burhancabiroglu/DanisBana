package com.danisbana.danisbanaapp.presentation.screen.admin.conversation

import androidx.lifecycle.ViewModel
import com.danisbana.danisbanaapp.presentation.screen.home.conversation.ConversationArgs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class AdminConversationViewModel @Inject constructor(): ViewModel() {
    private val _stateFlow: MutableStateFlow<AdminConversationState> = MutableStateFlow(AdminConversationState())
    val stateFlow: StateFlow<AdminConversationState> = _stateFlow.asStateFlow()


    fun setStateArgs(args: AdminConversationArgs?) {
        args?:return
        _stateFlow.value.loadingState.show()
        _stateFlow.value.channelName = args.message.title
        _stateFlow.value.message = args.message
        _stateFlow.value.loadingState.hide()
    }
}