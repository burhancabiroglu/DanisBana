package com.danisbana.danisbanaapp.domain.base

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.danisbana.danisbanaapp.presentation.components.indicator.PageLoading


@Composable
fun BaseScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.background,
    loadingState: LoadingState = rememberLoadingState(),
    snackBarHostState: SnackbarHostState = rememberSnackBarState(),
    dialogState: BaseDialogState = rememberDialogState(),
    pickerDialogState: BaseDialogState = rememberDialogState(),
    dialogAction:() -> Unit = {},
    pickerAction: (Uri) -> Unit = {},
    content: @Composable () -> Unit
) {
    Surface(modifier = modifier.fillMaxSize(), color = backgroundColor) {
        Box(modifier = modifier.fillMaxSize()){
            Scaffold(
                topBar = topBar
            ) {
                PageLoading(loadingState.isLoading)
                Box(modifier = Modifier.padding(it)) {
                    content()
                }
            }
            SnackbarHost(
                modifier = Modifier.align(Alignment.Center),
                hostState = snackBarHostState,
                snackbar = { BaseSnackBar(data = it) }
            )
            BaseDialog(dialogState,dialogAction)
            PickerDialog(pickerDialogState,pickerAction)
        }
    }
}


