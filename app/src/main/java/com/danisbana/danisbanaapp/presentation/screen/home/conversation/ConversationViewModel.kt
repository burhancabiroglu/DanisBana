package com.danisbana.danisbanaapp.presentation.screen.home.conversation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danisbana.danisbanaapp.core.model.message.MessageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ConversationViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<ConversationState> = MutableStateFlow(ConversationState())
    val stateFlow: StateFlow<ConversationState> = _stateFlow.asStateFlow()


    init {
        viewModelScope.launch {
            _stateFlow.tryEmit(
                ConversationState(
                    channelMembers = 1,
                    channelName = "Depresyon Şikayeti",
                    initialMessages = _initialMessages
                )
            )
        }
    }
}


val _initialMessages = listOf(
    MessageModel(
        "authorMe",
        "Check it out!",
        "8:07 PM"
    ),
    MessageModel(
        "me",
        "Thank you!",
        "8:06 PM",
    ),
    MessageModel(
        "authorMe",
        "Check it out!",
        "8:07 PM"
    ),
    MessageModel(
        "me",
        "Thank you!",
        "8:06 PM",
    ),
    MessageModel(
        "authorMe",
        "Check it out!",
        "8:07 PM"
    ),
    MessageModel(
        "me",
        "Thank you!",
        "8:06 PM",
    ),
    MessageModel(
        "authorMe",
        "Check it out!",
        "8:07 PM"
    ),
    MessageModel(
        "me",
        "Thank you!",
        "8:06 PM",
    ),
    MessageModel(
        "authorMe",
        "Check it out!",
        "8:07 PM"
    ),
    MessageModel(
        "me",
        "Thank you!",
        "8:06 PM",
    ),
    MessageModel(
        "authorMe",
        "Check it out!",
        "8:07 PM"
    ),
    MessageModel(
        "me",
        "Thank you!",
        "8:06 PM",
    ),
)
