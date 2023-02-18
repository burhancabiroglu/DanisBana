package com.danisbana.danisbanaapp.presentation.screen.home.profile

import com.danisbana.danisbanaapp.core.model.profile.AppUser


data class ProfileState(
    var appUser: AppUser? = null
)

data class ProfileActions(
    val onClick: () -> Unit = {}
)