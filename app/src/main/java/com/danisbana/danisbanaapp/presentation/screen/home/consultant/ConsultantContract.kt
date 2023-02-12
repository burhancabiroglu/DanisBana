package com.danisbana.danisbanaapp.presentation.screen.home.consultant

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue

class ConsultantState {
    var noteTitle by mutableStateOf(TextFieldValue())
    var noteDetail by mutableStateOf(TextFieldValue())
}