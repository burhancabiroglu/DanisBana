package com.danisbana.danisbanaapp.domain.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.danisbana.danisbanaapp.presentation.theme.Black20
import com.danisbana.danisbanaapp.presentation.theme.Red
import com.danisbana.danisbanaapp.presentation.theme.SuccessGreen
import com.danisbana.danisbanaapp.presentation.theme.White

@Composable
fun BaseSnackBar(data: SnackbarData) {
    rememberScrollState()
    val bgColor =
        if (data.actionLabel == "error") Red.copy(alpha = 0.4f)
        else SuccessGreen.copy(alpha = 0.6f)

    val icon =
        if (data.actionLabel == "error") Icons.Default.Error
        else Icons.Default.Check
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = White,
        modifier = Modifier
            .height(120.dp)
            .width(230.dp)
    ) {
        Box(
            Modifier
                .background(Black20)
                .fillMaxSize()
                .zIndex(100f),
            contentAlignment = Alignment.Center
        ) {
            Card(
                shape = RoundedCornerShape(8.dp),
                backgroundColor = White,
                modifier = Modifier
                    .height(120.dp)
                    .width(230.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(bgColor)
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(
                            4.dp,
                            Alignment.CenterVertically
                        ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = data.actionLabel,
                            tint = White
                        )
                        Text(text = data.message, color = White, textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}

@Composable
fun rememberSnackBarState() = remember { SnackbarHostState() }
