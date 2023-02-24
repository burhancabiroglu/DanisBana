package com.danisbana.danisbanaapp.core.model.message

import androidx.compose.ui.graphics.Color
import com.danisbana.danisbanaapp.presentation.theme.*

enum class MessageStatus(
    val label: String,
    val color: Color
) {
    PENDING(
        label = "Onay Bekleniyor",
        color = Marigold
    ),
    ACCEPTED(
        label = "Onaylandı",
        color = SuccessGreen
    ),
    ANSWERED(
        label = "Cevaplandı",
        color = Blue
    ),
    REJECTED(
        label = "Onaylanmadı",
        color = Red.copy(alpha = 0.8f)
    );
}