package com.danisbana.danisbanaapp.core.model.message

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

/**
 * generated id,
 * sender id,
 * status
 * timestamp
 * title,
 * content,
 * isPool
 */

@Parcelize
class MessageEntity(
    var id: String = UUID.randomUUID().toString(),
    var senderId: String = "",
    var consultantId:String? = null,
    var timestamp: Long = 0L,
    var title: String = "",
    var content: String = "",
    var statusOrdinal: Int = 0,
    var isPool: Boolean = true,
    var answer: Answer? = null
): Parcelable {
    @IgnoredOnParcel
    val status: MessageStatus get() = MessageStatus.values()[statusOrdinal]

    fun dateString(): String {
        @SuppressLint("SimpleDateFormat")
        val formatter = SimpleDateFormat("dd.MM.yyyy")
        return formatter.format(Date(timestamp))
    }
}


@Parcelize
data class Answer(
    val id: String = UUID.randomUUID().toString(),
    val timestamp: Long = 0L,
    val content: String = "",
): Parcelable {
    fun dateString(): String {
        @SuppressLint("SimpleDateFormat")
        val formatter = SimpleDateFormat("dd.MM.yyyy")
        return formatter.format(Date(timestamp))
    }
}