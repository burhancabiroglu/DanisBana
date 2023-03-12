package com.danisbana.danisbanaapp.presentation.screen.admin.panel

import android.util.Log
import androidx.compose.material.SnackbarHostState
import androidx.lifecycle.SavedStateHandle
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
class AdminPanelViewModel @Inject constructor(
    private val adminRepo: AdminRepo
) : ViewModel() {

    var snackBarState = SnackbarHostState()

    private val _stateFlow: MutableStateFlow<AdminPanelState> = MutableStateFlow(AdminPanelState())
    val stateFlow: StateFlow<AdminPanelState> = _stateFlow.asStateFlow()

    fun getData() {
        getAdminMessages()
        getPoolMessages()
    }

    private fun getAdminMessages() {
        viewModelScope.launch {
            _stateFlow.value.pageLoading.show()
            val result = adminRepo.getPersonalMessagesAsync().await()
            result.onFailure(::onFailure)
            result.onSuccess {
                _stateFlow.value.editorMessages = it
            }
            _stateFlow.value.pageLoading.hide()
        }
    }

    private fun getPoolMessages() {
        viewModelScope.launch {
            _stateFlow.value.pageLoading.show()
            val result = adminRepo.getMessagesPoolAsync().await()
            result.onFailure(::onFailure)
            result.onSuccess{
                _stateFlow.value.poolMessages = it
            }
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

}