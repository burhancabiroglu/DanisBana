package com.danisbana.danisbanaapp.presentation.screen.home.dashboard

import androidx.compose.material.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danisbana.danisbanaapp.application.Constants.MESSAGE_COST
import com.danisbana.danisbanaapp.domain.repo.FirebaseAuthRepo
import com.google.android.gms.ads.rewarded.RewardItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class DashboardViewModel @Inject constructor(
    val authRepo: FirebaseAuthRepo
) : ViewModel() {

    var snackBarState = SnackbarHostState()

    private val _stateFlow: MutableStateFlow<DashboardState> = MutableStateFlow(DashboardState(snackBarState))
    val stateFlow: StateFlow<DashboardState> = _stateFlow.asStateFlow()


    fun hasPoint(
        successCallBack: () -> Unit,
        errorCallBack: () -> Unit
    ) {
        viewModelScope.launch {
            val result = authRepo.getAppUserAsync().await()
            result.onSuccess {
                if ((it.info?.point ?: 0) < MESSAGE_COST) errorCallBack.invoke()
                else successCallBack.invoke()
            }
        }
    }

    fun showDialog() {
        _stateFlow.value.pointDialogState.open()
    }

    fun toggleLoading(it:Boolean) {
        _stateFlow.value.pointDialogState.hide()
        if (it) _stateFlow.value.loadingState.show()
        else _stateFlow.value.loadingState.hide()
    }

    fun adsSuccess(reward: RewardItem) {
        viewModelScope.launch {
            snackBarState.showSnackbar(
                actionLabel = "success",
                message = "Tebrikler,\n${reward.amount} lotus puan kazandın!"
            )
        }
        viewModelScope.launch {
            authRepo.appendPointAsync(reward = reward.amount).await()
        }
    }
}