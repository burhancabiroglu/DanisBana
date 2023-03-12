package com.danisbana.danisbanaapp.presentation.screen.home.root

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.danisbana.danisbanaapp.domain.repo.FirebaseAuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    authRepo: FirebaseAuthRepo,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<HomeState> = MutableStateFlow(
        HomeState(
            isAdmin = authRepo.userCacheValue?.info?.userRole?.admin == true
        )
    )
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