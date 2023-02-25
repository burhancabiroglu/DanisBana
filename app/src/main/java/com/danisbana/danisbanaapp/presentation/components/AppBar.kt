package com.danisbana.danisbanaapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danisbana.danisbanaapp.presentation.theme.White


@Composable
fun MAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    subTitle: (@Composable ()-> Unit)? = null,
    onBackAction: (() -> Unit)? = null,
    logoutAction: (() -> Unit)? = null
) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp) ,
        backgroundColor = White,
        elevation = 3.dp,
    ) {
        Box(
            modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h2.copy(fontSize = 18.sp),
                    color = MaterialTheme.colors.primary
                )
                subTitle?.invoke()
            }
            if(onBackAction != null) {
                Row(
                    modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconButton(onClick = onBackAction) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew ,
                            contentDescription = "back",
                            tint = MaterialTheme.colors.primary
                        )
                    }
                }
            }
            if(logoutAction != null){
                Row(
                    modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = logoutAction) {
                        Icon(
                            imageVector = Icons.Default.Logout ,
                            contentDescription = "Logout",
                            tint = MaterialTheme.colors.error.copy(0.5f)
                        )
                    }
                }
            }
        }
    }
}