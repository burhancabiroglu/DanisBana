package com.danisbana.danisbanaapp.presentation.screen.home.profile

import androidx.compose.material.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danisbana.danisbanaapp.core.model.profile.AppUser
import com.danisbana.danisbanaapp.domain.repo.FirebaseAuthRepo
import com.google.android.gms.ads.rewarded.RewardItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepo: FirebaseAuthRepo
) : ViewModel() {

    var snackBarState = SnackbarHostState()

    private val _stateFlow: MutableStateFlow<ProfileState> = MutableStateFlow(ProfileState(snackBarState))
    val stateFlow: StateFlow<ProfileState> = _stateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            _stateFlow.value.pageLoading.show()
            awaitAll(
                getAppUserAsync(),
                getTotalMessageCountAsync(),
                getAcceptedMessageCountAsync()
            )
            _stateFlow.value.pageLoading.hide()
        }
    }


    private suspend fun getAppUserAsync(): Deferred<Result<AppUser>> {
        return viewModelScope.async {
            val result = authRepo.getAppUserAsync().await()
            if(result.isSuccess) _stateFlow.value.appUser = result.getOrNull()
            return@async result
        }
    }

    private fun getAcceptedMessageCountAsync(): Deferred<Result<Int>> {
        return viewModelScope.async {
            val result = authRepo.getAcceptedMessageCountAsync().await()
            if(result.isSuccess) _stateFlow.value.acceptedMessages = result.getOrNull()
            return@async result
        }
    }

    private fun getTotalMessageCountAsync(): Deferred<Result<Int>> {
        return viewModelScope.async {
            val result = authRepo.getTotalMessageCountAsync().await()
            if(result.isSuccess) _stateFlow.value.totalMessages = result.getOrNull()
            return@async result
        }
    }

    fun updateProfilePicture(bytes: ByteArray) {
        viewModelScope.launch {
            _stateFlow.value.pageLoading.show()
            authRepo.updateProfilePictureAsync(bytes).await()
            val result = authRepo.getAppUserAsync().await()
            if(result.isSuccess) _stateFlow.value.appUser = result.getOrNull()
            _stateFlow.value.pageLoading.hide()
        }
    }

    fun logout() {
        authRepo.signOut()
    }

    fun toggleLoading(it:Boolean) {
        if (it) _stateFlow.value.pageLoading.show()
        else _stateFlow.value.pageLoading.hide()
    }

    fun adsSuccess(reward:RewardItem) {
        viewModelScope.launch {
            snackBarState.showSnackbar(
                actionLabel = "success",
                message = "Tebrikler,\n${reward.amount} lotus puan kazandın!"
            )
        }
        viewModelScope.launch {
            authRepo.appendPointAsync(reward = reward.amount).await()
            getAppUserAsync().await()
        }
    }
}