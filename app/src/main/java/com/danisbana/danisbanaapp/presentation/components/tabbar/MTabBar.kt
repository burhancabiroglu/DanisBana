package com.danisbana.danisbanaapp.presentation.components.tabbar

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TabRow
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.danisbana.danisbanaapp.presentation.components.bottomnav.BottomNavState
import com.danisbana.danisbanaapp.presentation.components.bottomnav.rememberBottomNavState
import com.danisbana.danisbanaapp.presentation.navigation.Screen
import com.danisbana.danisbanaapp.presentation.theme.AppDimens

@Composable
fun MTabBar(
    modifier: Modifier = Modifier,
    state: BottomNavState = rememberBottomNavState(),
    vararg items: TabItemUIObj,
    onItemCLick: ((Screen) -> Unit) = {}
) {
    TabRow(
        modifier = modifier
            .height(AppDimens.y50dp)
            .padding(AppDimens.s2dp),
        selectedTabIndex = state.selectedIndex,
        backgroundColor = Color(0x08232531),
        divider = {},
        indicator = { MarketTabIndicator(tabPosition = it[state.selectedIndex]) },
        tabs = {
            items.forEachIndexed { index, item ->
                val isSelected by remember { derivedStateOf { index == state.selectedIndex } }
                MTab(tabItemObj = item, isSelected = isSelected) {
                    state.selectedIndex = index
                    onItemCLick.invoke(item.route)
                }
            }
        }
    )
}