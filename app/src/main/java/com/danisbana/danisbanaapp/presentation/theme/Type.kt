package com.danisbana.danisbanaapp.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.danisbana.danisbanaapp.R

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins)),
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    ),
    h2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.nunito)),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    h3 = TextStyle(
        fontFamily = FontFamily(Font(R.font.nunito)),
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp
    ),
    body1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.nunito)),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    button = TextStyle(
        fontFamily = FontFamily(Font(R.font.nunito)),
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily(Font(R.font.nunito)),
        fontWeight = FontWeight.Bold,
        fontSize = 11.sp
    )
)