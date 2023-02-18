package com.danisbana.danisbanaapp.core.model.agreement

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Agreement(
    val label: String,
    val content: String,
    val isReady: Boolean
): Parcelable