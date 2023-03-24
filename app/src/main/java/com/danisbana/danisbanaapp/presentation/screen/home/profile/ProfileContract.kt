package com.danisbana.danisbanaapp.presentation.screen.home.profile

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.danisbana.danisbanaapp.core.model.profile.AppUser
import com.danisbana.danisbanaapp.domain.base.LoadingState
import com.google.android.gms.ads.rewarded.RewardItem


class ProfileState(
    val snackBarHostState: SnackbarHostState = SnackbarHostState()
) {
    var appUser: AppUser? by mutableStateOf(null)
    var pageLoading:LoadingState = LoadingState(false)
    var totalMessages: Int? by mutableStateOf(null)
    var acceptedMessages: Int? by mutableStateOf(null)
}

data class ProfileActions(
    var logout: () -> Unit = {},
    var updatePicture: (bytes: ByteArray) -> Unit = {},
    var loadingAction: (Boolean) -> Unit = {},
    var adsSuccess: (RewardItem) -> Unit = {}
)