package com.danisbana.danisbanaapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danisbana.danisbanaapp.R
import com.danisbana.danisbanaapp.presentation.theme.CadetBlue
import com.danisbana.danisbanaapp.presentation.theme.DanisBanaAppTheme
import com.danisbana.danisbanaapp.presentation.theme.QueenBlue
import com.danisbana.danisbanaapp.presentation.theme.White

enum class InputSelector {
    NONE,
}

@Composable
fun UserInput(
    onMessageSent: (String) -> Unit,
    modifier: Modifier = Modifier,
    resetScroll: () -> Unit = {},
) {
    var currentInputSelector by rememberSaveable { mutableStateOf(InputSelector.NONE) }
    val dismissKeyboard = { currentInputSelector = InputSelector.NONE }

    // Intercept back navigation if there's a InputSelector visible
    if (currentInputSelector != InputSelector.NONE) {
        BackPressHandler(onBackPressed = dismissKeyboard)
    }

    var textState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }

    // Used to decide if the keyboard should be shown
    var textFieldFocusState by remember { mutableStateOf(false) }

    Surface {
        Column(modifier = modifier) {
            Divider()
            UserInputText(
                textFieldValue = textState,
                onTextChanged = { textState = it },
                // Only show the keyboard if there's no input selector and text field has focus
                keyboardShown = currentInputSelector == InputSelector.NONE && textFieldFocusState,
                // Close extended selector if text field receives focus
                onTextFieldFocused = { focused ->
                    if (focused) {
                        currentInputSelector = InputSelector.NONE
                        resetScroll()
                    }
                    textFieldFocusState = focused
                },
                focusState = textFieldFocusState,
                sendButton = {
                    SendButton(
                        sendMessageEnabled = textState.text.isNotBlank(),
                        onMessageSent = {
                            onMessageSent(textState.text)
                            textState = TextFieldValue()
                            resetScroll()
                            dismissKeyboard()
                        },
                    )
                }
            )
        }
    }
}

@Composable
private fun SendButton(
    sendMessageEnabled: Boolean,
    onMessageSent: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (sendMessageEnabled) QueenBlue else CadetBlue
    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        IconButton(
            modifier = modifier.background(
                backgroundColor,
                CircleShape
            ).size(42.dp),
            enabled = sendMessageEnabled,
            onClick = onMessageSent
        ) {
            Icon(
                modifier = modifier.size(14.dp),
                imageVector = Icons.Default.Send,
                tint = White,
                contentDescription = ""
            )
        }
    }
}

val KeyboardShownKey = SemanticsPropertyKey<Boolean>("KeyboardShownKey")
var SemanticsPropertyReceiver.keyboardShownProperty by KeyboardShownKey

@Composable
private fun UserInputText(
    keyboardType: KeyboardType = KeyboardType.Text,
    onTextChanged: (TextFieldValue) -> Unit,
    textFieldValue: TextFieldValue,
    keyboardShown: Boolean,
    onTextFieldFocused: (Boolean) -> Unit,
    focusState: Boolean,
    sendButton: (@Composable () -> Unit)? = {}
) {
    val a11ylabel = stringResource(id = R.string.textfield_desc)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .semantics {
                contentDescription = a11ylabel
                keyboardShownProperty = keyboardShown
            },
        horizontalArrangement = Arrangement.End
    ) {
        Surface(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier
                    .height(64.dp)
                    .align(Alignment.Bottom)
            ) {
                var lastFocusState by remember { mutableStateOf(false) }
                BasicTextField(
                    value = textFieldValue,
                    onValueChange = { onTextChanged(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp)
                        .align(Alignment.CenterStart)
                        .onFocusChanged { state ->
                            if (lastFocusState != state.isFocused) {
                                onTextFieldFocused(state.isFocused)
                            }
                            lastFocusState = state.isFocused
                        },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = keyboardType,
                        imeAction = androidx.compose.ui.text.input.ImeAction.Send
                    ),
                    maxLines = 5,
                    cursorBrush = SolidColor(LocalContentColor.current),
                    textStyle = MaterialTheme.typography.body2
                )

                val disableContentColor = MaterialTheme.colors.primaryVariant
                if (textFieldValue.text.isEmpty() && !focusState) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 32.dp),
                        text = stringResource(id = R.string.textfield_hint),
                        style = MaterialTheme.typography.body2.copy(color = disableContentColor)
                    )
                }
            }
        }
        sendButton?.invoke()
    }
}


@Preview
@Composable
fun UserInputPreview() {
    DanisBanaAppTheme {
        UserInput(
            onMessageSent = {},
            resetScroll = {},
            modifier = Modifier
                .navigationBarsPadding()
                .imePadding(),
        )
    }
}