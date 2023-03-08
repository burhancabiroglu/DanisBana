package com.danisbana.danisbanaapp.presentation.screen.admin.pool

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class MessagePoolViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<MessagePoolState> = MutableStateFlow(MessagePoolState())
    val stateFlow: StateFlow<MessagePoolState> = _stateFlow.asStateFlow()

}