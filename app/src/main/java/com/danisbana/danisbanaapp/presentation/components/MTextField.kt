package com.danisbana.danisbanaapp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.*
import com.danisbana.danisbanaapp.presentation.theme.AppDimens
import com.danisbana.danisbanaapp.presentation.theme.CadetBlue
import com.danisbana.danisbanaapp.presentation.theme.Red
import com.danisbana.danisbanaapp.presentation.theme.Transparent

@Composable
fun MTextField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: String,
) {
    return TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        modifier = modifier.fillMaxWidth().wrapContentHeight(),
        label = { Text(text = label) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(0x90E8E8E8),
            unfocusedIndicatorColor = Transparent,
            focusedIndicatorColor = Transparent,
            errorIndicatorColor = Red.copy(alpha = 0.6f)
        ),
        shape = RoundedCornerShape(AppDimens.s12dp),
        textStyle = MaterialTheme.typography.body2,
        keyboardOptions = KeyboardOptions(
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        )
    )
}

@Composable
fun MEmailTextField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: String,
) {
    return TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        modifier = modifier.fillMaxWidth().wrapContentHeight(),
        label = { Text(text = label) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(0x90E8E8E8),
            unfocusedIndicatorColor = Transparent,
            focusedIndicatorColor = Transparent,
            errorIndicatorColor = Red.copy(alpha = 0.6f)
        ),
        shape = RoundedCornerShape(AppDimens.s12dp),
        textStyle = MaterialTheme.typography.body2,
        keyboardOptions = KeyboardOptions(
            autoCorrect = true,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )
    )
}

@Composable
fun MTextFieldVariant(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    placeholder: String,
) {
    return OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        placeholder = { Text(text = placeholder) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(0x90E8E8E8),
            unfocusedIndicatorColor = Transparent,
            focusedIndicatorColor = CadetBlue,
            errorIndicatorColor = Red.copy(alpha = 0.6f)
        ),
        shape = RoundedCornerShape(AppDimens.s12dp),
        textStyle = MaterialTheme.typography.body2,
    )
}


@Composable
fun MPasswordTextField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: String,
) {
    var passwordVisibility by remember { mutableStateOf(false) }
    return TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        modifier = modifier.fillMaxWidth().wrapContentHeight(),
        label = { Text(text = label) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(0x90E8E8E8),
            unfocusedIndicatorColor = Transparent,
            focusedIndicatorColor = Transparent
        ),
        shape = RoundedCornerShape(AppDimens.s12dp),
        textStyle = MaterialTheme.typography.body2,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = {
                passwordVisibility = !passwordVisibility
            }) {
                Icon(
                    imageVector = if(passwordVisibility.not()) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = "Visibility Icon"
                )
            }
        }
    )
}