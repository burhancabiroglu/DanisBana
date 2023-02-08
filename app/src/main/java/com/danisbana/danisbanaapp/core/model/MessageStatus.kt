package com.danisbana.danisbanaapp.core.model

import androidx.compose.ui.graphics.Color

enum class MessageStatus(
    val label: String,
    val color: Color
) {
    ACCEPTED(
        label = "Onaylandı",
        color = Color.Green
    ),
    REJECTED(
        label = "Onaylanmadı",
        color = Color.Red
    ),
    PENDING(
        label = "Onay Bekleniyor",
        color = Color.Yellow
    ),
    ANSWERED(
        label = "Cevaplandı",
        color = Color.Blue
    )
}