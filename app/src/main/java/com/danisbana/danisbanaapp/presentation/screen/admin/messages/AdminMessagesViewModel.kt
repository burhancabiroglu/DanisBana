package com.danisbana.danisbanaapp.presentation.screen.admin.messages

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class AdminMessagesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<AdminMessagesState> = MutableStateFlow(AdminMessagesState())
    val stateFlow: StateFlow<AdminMessagesState> = _stateFlow.asStateFlow()

}