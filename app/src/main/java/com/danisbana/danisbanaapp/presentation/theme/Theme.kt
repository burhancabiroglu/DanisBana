package com.danisbana.danisbanaapp.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = QueenBlue,
    onPrimary = Charcoal,
    primaryVariant = Grey100,
    secondary = DesertSand,
    onSecondary = Marigold,
    background =  White,
    onBackground = Charcoal.copy(0.9f),
    surface = Grey100,
    onSurface = Charcoal,
)

@Composable
fun DanisBanaAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    /*val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }*/

    val colors = LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}