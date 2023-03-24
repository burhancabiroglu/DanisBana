package com.danisbana.danisbanaapp.presentation.screen.home.messages

import androidx.compose.material.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danisbana.danisbanaapp.core.model.message.MessageEntity
import com.danisbana.danisbanaapp.domain.repo.FirebaseAuthRepo
import com.google.android.gms.ads.rewarded.RewardItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MessagesViewModel @Inject constructor(
    private val authRepo: FirebaseAuthRepo
) : ViewModel() {

    var snackBarState = SnackbarHostState()

    private val _stateFlow: MutableStateFlow<MessagesState> = MutableStateFlow(MessagesState(snackBarState))
    val stateFlow: StateFlow<MessagesState> = _stateFlow.asStateFlow()

    fun getData() {
        viewModelScope.launch {
            _stateFlow.value.pageLoading.show()
            val result = authRepo.getUserMessagesAsync().await()
            result.onFailure(::onFailure)
            result.onSuccess(::onSuccess)
            _stateFlow.value.pageLoading.hide()
        }
    }

    private fun onFailure(t: Throwable) {
        viewModelScope.launch {
            snackBarState.showSnackbar(
                actionLabel = "error",
                message = "Hata meydana geldi"
            )
        }
    }

    fun deleteMessage(id:String) {
        viewModelScope.launch {
            _stateFlow.value.pageLoading.show()
            val deleteResult = authRepo.deleteMessageAsync(id).await()
            deleteResult.onFailure(::onFailure)
            val result = authRepo.getUserMessagesAsync().await()
            result.onFailure(::onFailure)
            result.onSuccess(::onSuccess)
            _stateFlow.value.pageLoading.hide()
        }
    }

    private fun onSuccess(value:List<MessageEntity>) {
        _stateFlow.value.messages = value
    }

    fun hasPoint(
        successCallBack: () -> Unit,
        errorCallBack: () -> Unit
    ) {
        viewModelScope.launch {
            val result = authRepo.getAppUserAsync().await()
            result.onSuccess {
                if ((it.info?.point ?: 0) < 60) errorCallBack.invoke()
                else successCallBack.invoke()
            }
        }
    }

    fun showDialog() {
        _stateFlow.value.pointDialogState.open()
    }

    fun toggleLoading(it:Boolean) {
        _stateFlow.value.pointDialogState.hide()
        if (it) _stateFlow.value.pageLoading.show()
        else _stateFlow.value.pageLoading.hide()
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