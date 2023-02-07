package com.danisbana.danisbanaapp.presentation.components

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieView(
    @RawRes res: Int,
    modifier: Modifier = Modifier,
    replay: Boolean = false
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(res))
    LottieAnimation(
        modifier = modifier.fillMaxSize(),
        speed = 1f,
        iterations = if (replay) 100 else 1,
        composition = composition,
        clipToCompositionBounds = true,
        maintainOriginalImageBounds = true
    )
}