package com.danisbana.danisbanaapp.core.model.message

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostMessage(
    val to: String,
    val data: PostMessageData
): Parcelable



@Parcelize
data class PostMessageData(
    val title: String,
    val body: String,
): Parcelable