package com.danisbana.danisbanaapp.presentation.screen.admin.panel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.danisbana.danisbanaapp.domain.repo.AdminRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class AdminPanelViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val adminRepo: AdminRepo
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<AdminPanelState> = MutableStateFlow(AdminPanelState())
    val stateFlow: StateFlow<AdminPanelState> = _stateFlow.asStateFlow()

}