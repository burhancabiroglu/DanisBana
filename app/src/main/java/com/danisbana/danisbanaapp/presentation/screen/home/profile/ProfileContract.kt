package com.danisbana.danisbanaapp.presentation.screen.home.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.danisbana.danisbanaapp.core.model.profile.AppUser
import com.danisbana.danisbanaapp.domain.base.LoadingState



class ProfileState {
    var appUser: AppUser? by mutableStateOf(null)
    var pageLoading:LoadingState = LoadingState(false)
}

data class ProfileActions(
    var logout: () -> Unit = {}
)