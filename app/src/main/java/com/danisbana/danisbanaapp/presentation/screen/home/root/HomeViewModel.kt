package com.danisbana.danisbanaapp.presentation.screen.home.root

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _stateFlow: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val stateFlow: StateFlow<HomeState> = _stateFlow.asStateFlow()
}