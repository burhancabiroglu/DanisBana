package com.danisbana.danisbanaapp.presentation.screen.splash


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danisbana.danisbanaapp.presentation.components.LottieView
import com.danisbana.danisbanaapp.R
import com.danisbana.danisbanaapp.presentation.theme.DesertSand
import com.danisbana.danisbanaapp.presentation.theme.QueenBlue
import com.danisbana.danisbanaapp.presentation.theme.Transparent
import com.danisbana.danisbanaapp.presentation.theme.White

@Composable
fun SplashScreen(
    state: SplashState = SplashState(),
    actions: SplashActions = SplashActions(),
) {
    val anim = animateFloatAsState(
        targetValue = if (state.startAnim) 0.7f else 0f,
        animationSpec = tween(durationMillis = 2000)
    )
    Box(
        Modifier
            .background(
                brush =
                    Brush.verticalGradient(
                        colors = listOf(
                            QueenBlue,
                            DesertSand,
                            QueenBlue,
                        )
                    )
            )
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentAlignment = Alignment.BottomCenter
        ) {
            LottieView(res = R.raw.lottie_anim_lotus)
            Text(
                modifier = Modifier
                    .offset(y = (anim.value * -70 - 30).dp)
                    .background(Transparent),
                text = stringResource(id = R.string.app_name),
                style = TextStyle(
                    color = White.copy(alpha = anim.value),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
            )
        }

    }
    
}

@Composable
@Preview(name = "Splash")
private fun SplashScreenPreview() {
    SplashScreen()
}

