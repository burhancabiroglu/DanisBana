package com.danisbana.danisbanaapp.core.model

import androidx.compose.ui.graphics.Color
import com.danisbana.danisbanaapp.presentation.theme.*

enum class MessageStatus(
    val label: String,
    val color: Color
) {
    ACCEPTED(
        label = "Onaylandı",
        color = SuccessGreen
    ),
    REJECTED(
        label = "Onaylanmadı",
        color = Red.copy(alpha = 0.8f)
    ),
    PENDING(
        label = "Onay Bekleniyor",
        color = Marigold
    ),
    ANSWERED(
        label = "Cevaplandı",
        color = Blue
    )
}