package com.danisbana.danisbanaapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danisbana.danisbanaapp.presentation.theme.White

@Composable
fun MAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    logoutEnabled: Boolean = false,
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
            Row(
                modifier.fillMaxWidth(),

            ) {
                Box(modifier.weight(1f))
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Box(
                    modifier.weight(1f),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    if (logoutEnabled) {
                        Icon(
                            imageVector = Icons.Default.Logout ,
                            contentDescription = "",
                        )
                    }
                }
            }
        }
    }
}