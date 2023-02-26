package com.danisbana.danisbanaapp.presentation.screen.home.messages

import androidx.compose.material.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danisbana.danisbanaapp.core.model.message.MessageEntity
import com.danisbana.danisbanaapp.domain.repo.FirebaseAuthRepo
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
}