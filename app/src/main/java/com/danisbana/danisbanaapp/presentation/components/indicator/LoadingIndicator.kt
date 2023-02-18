package com.danisbana.danisbanaapp.presentation.components.indicator

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.danisbana.danisbanaapp.presentation.theme.*


@Composable
fun PageLoading(
    visibility: Boolean = false
) {
    if(visibility) Box(
        Modifier.background(Black20).fillMaxSize().zIndex(100f),
        contentAlignment = Alignment.Center
    ) {
        LoadingIndicator()
    }
}

@Composable
fun LoadingIndicator(
    indicatorSize: Dp = 70.dp,
    circleColors: List<Color> = listOf(
        QueenBlue,
        LightSeaGreen,
        CadetBlue,
        DesertSand
    ),
    animationDuration: Int = 360,
) {

    val infiniteTransition = rememberInfiniteTransition()

    val rotateAnimation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = animationDuration,
                easing = LinearEasing
            )
        )
    )

    CircularProgressIndicator(
        modifier = Modifier
            .size(size = indicatorSize)
            .rotate(degrees = rotateAnimation)
            .border(
                width = 4.dp,
                brush = Brush.sweepGradient(circleColors),
                shape = CircleShape
            ),
        progress = 1f,
        strokeWidth = 1.dp,
        color = MaterialTheme.colors.background // Set background color
    )
}

@Preview(name = "LoadingIndicator")
@Composable
private fun PreviewLoadingIndicator() {
    LoadingIndicator()
}
