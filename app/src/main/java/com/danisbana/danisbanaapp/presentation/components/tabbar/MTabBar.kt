package com.danisbana.danisbanaapp.presentation.components.tabbar

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TabRow
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.danisbana.danisbanaapp.presentation.navigation.Screen
import com.danisbana.danisbanaapp.presentation.theme.AppDimens

@Composable
fun MTabBar(
    modifier: Modifier = Modifier,
    vararg items: TabItemUIObj,
    onItemCLick: ((Screen) -> Unit) = {}
) {
    var selectedIndex by remember { mutableStateOf(0) }
    TabRow(
        modifier = modifier
            .height(AppDimens.y50dp)
            .padding(AppDimens.s2dp),
        selectedTabIndex = selectedIndex,
        backgroundColor = Color(0x08232531),
        divider = {},
        indicator = { MarketTabIndicator(tabPosition = it[selectedIndex]) },
        tabs = {
            items.forEachIndexed { index, item ->
                val isSelected by remember { derivedStateOf { index == selectedIndex } }
                MTab(tabItemObj = item, isSelected = isSelected) {
                    selectedIndex = index
                    onItemCLick.invoke(item.route)
                }
            }
        }
    )
}