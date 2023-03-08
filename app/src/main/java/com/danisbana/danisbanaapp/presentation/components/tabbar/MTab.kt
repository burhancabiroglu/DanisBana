package com.danisbana.danisbanaapp.presentation.components.tabbar

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

@Composable
fun MTab(
    modifier: Modifier = Modifier,
    tabItemObj: TabItemUIObj,
    isSelected: Boolean = false,
    onClick: (() -> Unit) = {}
) {
    val textColor = remember(isSelected) { if(isSelected) White else Color(0x4D232531) }
    return Tab(
        modifier = modifier.zIndex(0.1f),
        selected = isSelected,
        onClick = onClick,
        unselectedContentColor = Transparent,
        selectedContentColor = Transparent,
        content = {
            Text(
                text = tabItemObj.label,
                color = textColor,
                style = MaterialTheme.typography.h2,
                fontSize = 16.sp)
        }
    )
}
