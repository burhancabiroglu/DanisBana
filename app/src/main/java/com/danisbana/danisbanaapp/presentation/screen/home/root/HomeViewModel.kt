package com.danisbana.danisbanaapp.presentation.screen.home.root

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val stateFlow: StateFlow<HomeState> = _stateFlow.asStateFlow()

    private val _navChannel = Channel<HomeNavChannel>()
    val navChannel get() = _navChannel.receiveAsFlow()


    fun routeConsultant() {
        viewModelScope.launch {
            _navChannel.send(HomeNavChannel.RouteConsultant)
        }
    }
}