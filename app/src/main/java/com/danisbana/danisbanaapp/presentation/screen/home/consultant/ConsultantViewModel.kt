package com.danisbana.danisbanaapp.presentation.screen.home.consultant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danisbana.danisbanaapp.domain.repo.FirebaseAuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ConsultantViewModel @Inject constructor(
    private val authRepo: FirebaseAuthRepo,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<ConsultantState> = MutableStateFlow(ConsultantState())
    val stateFlow: StateFlow<ConsultantState> = _stateFlow.asStateFlow()

    fun submit() {
        val state  = _stateFlow.value
        viewModelScope.launch {
            _stateFlow.value.pageLoading.show()
            authRepo.createMessageAsync(state.noteTitle.text,state.noteDetail.text).await()
            _stateFlow.value.pageLoading.hide()
        }
    }

}