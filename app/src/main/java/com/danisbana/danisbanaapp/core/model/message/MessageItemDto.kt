package com.danisbana.danisbanaapp.core.model.message

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.time.Instant
import java.util.Date

@Parcelize
data class MessageItemDto(
    val userName: String,
    val title: String,
    val shortMessage: String,
    val statusOrdinal: Int,
    val latestDateString: String,
    val createdAtString: String,
): Parcelable {

    @IgnoredOnParcel
    var latestDate: Date? = null
    @IgnoredOnParcel
    var createdAt: Date? = null
    @IgnoredOnParcel
    var status: MessageStatus = MessageStatus.values()[statusOrdinal]

}


val SampleMessageItemDto = MessageItemDto(
    userName = "Psk. Ahmed Arslan",
    title = "Depresyon Şikayeti",
    shortMessage = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
    statusOrdinal = 1,
    latestDateString = "14:48",
    createdAtString = Date.from(Instant.now()).toString()
)

val AcceptedMessageItemDto = MessageItemDto(
    userName = "Psk. Ahmed Arslan",
    title = "Depresyon Şikayeti",
    shortMessage = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
    statusOrdinal = 0,
    latestDateString = "12:53",
    createdAtString = Date.from(Instant.now()).toString()
)

val sampleItems = arrayListOf(
    AcceptedMessageItemDto,
    SampleMessageItemDto,
    AcceptedMessageItemDto.copy(statusOrdinal = 2),
    AcceptedMessageItemDto.copy(statusOrdinal = 3)
)