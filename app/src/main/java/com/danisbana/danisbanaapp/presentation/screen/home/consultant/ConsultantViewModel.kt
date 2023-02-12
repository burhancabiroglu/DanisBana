package com.danisbana.danisbanaapp.presentation.screen.home.consultant

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class ConsultantViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<ConsultantState> = MutableStateFlow(ConsultantState())
    val stateFlow: StateFlow<ConsultantState> = _stateFlow.asStateFlow()

}