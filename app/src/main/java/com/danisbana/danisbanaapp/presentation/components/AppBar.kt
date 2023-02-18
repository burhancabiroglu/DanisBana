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
import androidx.navigation.NavHostController
import com.danisbana.danisbanaapp.core.model.message.MessageStatus
import com.danisbana.danisbanaapp.presentation.screen.home.messages.components.StatusPointerCircle
import com.danisbana.danisbanaapp.presentation.theme.White

@Composable
fun MAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    logoutEnabled: Boolean = false,
    navIconEnabled: Boolean = false,
    navHostController: NavHostController? = null,
    logoutAction: ()-> Unit = {}
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
            Text(
                text = title,
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.primary
            )
            Row(
                modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                if (navIconEnabled) {
                    IconButton(onClick = {
                        navHostController?.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew ,
                            contentDescription = "back",
                            tint = MaterialTheme.colors.primary
                        )
                    }
                }
            }
            Row(
                modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                if (logoutEnabled) {
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



@Composable
fun MAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    navHostController: NavHostController? = null,
    messageStatus: MessageStatus
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
                StatusPointerCircle(status = messageStatus)
            }
            Row(
                modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = {
                    navHostController?.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew ,
                        contentDescription = "back",
                        tint = MaterialTheme.colors.primary
                    )
                }
            }
        }
    }
}