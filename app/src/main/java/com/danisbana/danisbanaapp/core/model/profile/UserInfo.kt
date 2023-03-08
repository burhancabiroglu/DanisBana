package com.danisbana.danisbanaapp.core.model.profile

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(
    val id: String = "",
    val totalMessages: Int = 0,
    val acceptedMessages: Int = 0,
    val point: Int = 0,
    val userRole: UserRole = UserRole()
):Parcelable

@Parcelize
data class UserRole(
    val admin: Boolean = false,
    val editor: Boolean = false,
    val isBlocked: Boolean = false
): Parcelable