package com.danisbana.danisbanaapp.presentation.components.tabbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.zIndex
import com.danisbana.danisbanaapp.presentation.theme.AppDimens
import com.danisbana.danisbanaapp.presentation.theme.QueenBlue

@Composable
fun MarketTabIndicator(
    modifier: Modifier = Modifier,
    tabPosition: TabPosition
) {
    Box(
        modifier = modifier
            .zIndex(0f)
            .tabIndicatorOffset(tabPosition)
            .clip(RoundedCornerShape(8))
            .fillMaxWidth()
            .height(AppDimens.y150dp)
            .background(QueenBlue),
    )
}