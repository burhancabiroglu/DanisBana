package com.danisbana.danisbanaapp.presentation.screen.home.root

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danisbana.danisbanaapp.core.repo.SharedPrefRepo
import com.danisbana.danisbanaapp.domain.repo.FirebaseAuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authRepo: FirebaseAuthRepo,
    private val sharedPref: SharedPrefRepo,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {
        viewModelScope.launch {
            authRepo.updateFCMTokenAsync(sharedPref.fcmToken.toString()).await()
        }
    }

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