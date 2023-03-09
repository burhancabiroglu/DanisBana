package com.danisbana.danisbanaapp.presentation.screen.admin.pool

import androidx.compose.material.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danisbana.danisbanaapp.core.model.message.MessageEntity
import com.danisbana.danisbanaapp.domain.repo.AdminRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MessagePoolViewModel @Inject constructor(
    private val adminRepo: AdminRepo
) : ViewModel() {
    var snackBarState = SnackbarHostState()

    private val _stateFlow: MutableStateFlow<MessagePoolState> = MutableStateFlow(MessagePoolState(snackBarState))
    val stateFlow: StateFlow<MessagePoolState> = _stateFlow.asStateFlow()


    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            _stateFlow.value.pageLoading.show()
            val result = adminRepo.getMessagesPoolAsync().await()
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

   /*fun deleteMessage(id:String) {
        viewModelScope.launch {
            _stateFlow.value.pageLoading.show()
            val deleteResult = authRepo.deleteMessageAsync(id).await()
            deleteResult.onFailure(::onFailure)
            val result = authRepo.getUserMessagesAsync().await()
            result.onFailure(::onFailure)
            result.onSuccess(::onSuccess)
            _stateFlow.value.pageLoading.hide()
        }
    }*/

    private fun onSuccess(value:List<MessageEntity>) {
        _stateFlow.value.messages = value
    }
}