package com.danisbana.danisbanaapp.core.model.profile

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(
    val id: String = "",
    val point: Int = 0,
    val userRole: UserRole = UserRole(),
    val cloudToken: String? = "",
):Parcelable

@Parcelize
data class UserRole(
    val admin: Boolean = false,
    val isBlocked: Boolean = false
): Parcelable