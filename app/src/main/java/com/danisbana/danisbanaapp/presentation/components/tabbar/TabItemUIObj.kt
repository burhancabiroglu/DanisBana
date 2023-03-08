package com.danisbana.danisbanaapp.presentation.components.tabbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.danisbana.danisbanaapp.presentation.navigation.Screen

class TabItemUIObj(
    val label: String = "",
    val route: Screen
) {
    companion object {
        fun generate() = arrayOf(
            TabItemUIObj(
                label = "Mesajlar",
                route = Screen.AdminMessages
            ),
            TabItemUIObj(
                label = "Mesaj Havuzu",
                route = Screen.MessagePool
            ),
        )
    }
}

@Composable
fun rememberTabItems(): Array<TabItemUIObj> {
    return remember { TabItemUIObj.generate() }
}