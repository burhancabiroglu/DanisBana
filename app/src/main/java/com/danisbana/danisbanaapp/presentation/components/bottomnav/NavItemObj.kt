package com.danisbana.danisbanaapp.presentation.components.bottomnav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import com.danisbana.danisbanaapp.presentation.navigation.Screen

class NavItemObj(
    val label: String = "",
    val icon: ImageVector,
    val route: Screen? = null
) {
    companion object {
        fun generate(): Array<NavItemObj> {
            return arrayOf(
                NavItemObj(
                    icon = Icons.Default.Home,
                    route = Screen.Dashboard
                ),
                NavItemObj(
                    icon = Icons.Default.Email,
                    route = Screen.ConditionalScreen
                ),
                NavItemObj(
                    icon = Icons.Default.Person,
                    route = Screen.Profile
                ),
            )
        }
    }
}

@Composable
fun getBottomNavigationItems() = remember {
    NavItemObj.generate()
}

