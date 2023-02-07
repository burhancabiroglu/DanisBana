package com.danisbana.danisbanaapp.presentation.components.bottomnav

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.danisbana.danisbanaapp.presentation.theme.AppDimens
import com.danisbana.danisbanaapp.presentation.theme.QueenBlue
import com.danisbana.danisbanaapp.presentation.theme.White


@Composable
fun MBottomNavigationBar(
    modifier: Modifier = Modifier,
    vararg items: NavItemObj,
    onItemCLick: ((String) -> Unit) = {}
) {
    var selectedIndex by remember { mutableStateOf(0) }
    BottomNavigation(
        modifier = modifier
            .height(AppDimens.bottomNavHeight),
        backgroundColor = White,
        elevation = 16.dp,
        contentColor = QueenBlue,
        content = {
            items.forEachIndexed { index, it ->
                val isSelected = (index == selectedIndex)
                BottomNavigationItem(
                    selected = isSelected,
                    unselectedContentColor = QueenBlue.copy(alpha = 0.3f),
                    selectedContentColor = QueenBlue,
                    icon = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = it.icon,
                                contentDescription = it.label,
                                modifier = modifier.size(AppDimens.s28dp)
                            )
                            AnimatedVisibility(isSelected) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Spacer(modifier.size(AppDimens.s8dp))
                                    Box(modifier.size(AppDimens.s6dp).background(QueenBlue, CircleShape))
                                }
                            }
                        }
                    },
                    onClick = {
                        onItemCLick.invoke(items[index].route?.route.toString())
                        selectedIndex = index
                    },
                    alwaysShowLabel = false,
                )
            }
        }
    )
}