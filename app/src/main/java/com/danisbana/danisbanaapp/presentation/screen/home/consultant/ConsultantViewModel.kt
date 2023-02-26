package com.danisbana.danisbanaapp.presentation.screen.home.consultant

import androidx.compose.material.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danisbana.danisbanaapp.domain.repo.FirebaseAuthRepo
import com.google.firebase.firestore.DocumentReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ConsultantViewModel @Inject constructor(
    private val authRepo: FirebaseAuthRepo,
) : ViewModel() {

    var snackBarState = SnackbarHostState()

    private val _stateFlow: MutableStateFlow<ConsultantState> = MutableStateFlow(ConsultantState(snackBarState))
    val stateFlow: StateFlow<ConsultantState> = _stateFlow.asStateFlow()

    private val _navChannel = Channel<ConsultantNavChannel>()
    val navChannel get() = _navChannel.receiveAsFlow()

    fun submit() {
        val state  = _stateFlow.value
        viewModelScope.launch {
            _stateFlow.value.pageLoading.show()
            val result = authRepo.createMessageAsync(state.noteTitle.text,state.noteDetail.text).await()
            result.onFailure(::onFailure)
            result.onSuccess(::onSuccess)
            _stateFlow.value.pageLoading.hide()
        }
    }

    private fun onFailure(t: Throwable) {
        viewModelScope.launch {
            snackBarState.showSnackbar(
                actionLabel = "error",
                message = "Bir hata meydana geldi"
            )
        }
    }

    private fun onSuccess(ref: DocumentReference) {
        viewModelScope.launch {
            snackBarState.showSnackbar(
                actionLabel = "success",
                message = "Notunuz Alınmıştır"
            )
        }
        viewModelScope.launch {
            delay(1200)
            _navChannel.trySend(ConsultantNavChannel.OnBack)
        }
    }
}