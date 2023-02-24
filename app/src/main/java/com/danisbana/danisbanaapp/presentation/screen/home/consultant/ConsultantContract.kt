package com.danisbana.danisbanaapp.presentation.screen.home.consultant

import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import com.danisbana.danisbanaapp.domain.base.LoadingState


class ConsultantState {
    var noteTitle by mutableStateOf(TextFieldValue())
    var noteDetail by mutableStateOf(TextFieldValue())

    var pageLoading = LoadingState(false)

    val buttonState by derivedStateOf {
        noteDetail.text.isNotEmpty() && noteTitle.text.isNotEmpty()
    }

}

data class ConsultantActions(
    val onSubmit: () -> Unit = {}
)

