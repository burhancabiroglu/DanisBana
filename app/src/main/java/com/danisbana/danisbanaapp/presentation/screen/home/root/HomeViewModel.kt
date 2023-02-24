package com.danisbana.danisbanaapp.presentation.screen.home.root

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val stateFlow: StateFlow<HomeState> = _stateFlow.asStateFlow()

    fun onRecompose() {
        savedStateHandle.get<Int>("bottom_nav_index")?.let {
            _stateFlow.value.bottomNavState.selectedIndex = it
        }
    }

    fun onDispose() {
        savedStateHandle["bottom_nav_index"] = _stateFlow.value.bottomNavState.selectedIndex
    }
}