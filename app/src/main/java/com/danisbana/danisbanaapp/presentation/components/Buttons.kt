package com.danisbana.danisbanaapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.danisbana.danisbanaapp.presentation.theme.CadetBlue
import com.danisbana.danisbanaapp.presentation.theme.Charcoal
import com.danisbana.danisbanaapp.presentation.theme.White

@Composable
fun BadgeButton(modifier: Modifier = Modifier, label: String, onClick: () -> Unit = {}) {
    Button(
        modifier = modifier
            .wrapContentSize()
            .shadow(
                elevation = 3.dp,
                clip = false,
                spotColor = CadetBlue,
                ambientColor = CadetBlue,
                shape = RoundedCornerShape(12.dp),
            ),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = White
        ),
        elevation = ButtonDefaults.elevation(0.dp),
        onClick = onClick
    ) {
        Text(
            modifier = modifier.padding(3.dp),
            text = label,
            style = MaterialTheme.typography.button
        )
    }
}


@Composable
fun WhiteButton(modifier: Modifier = Modifier, label: String, onClick: () -> Unit = {}) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp)
            .shadow(
                elevation = 3.dp,
                clip = false,
                spotColor = CadetBlue,
                ambientColor = CadetBlue,
                shape = RoundedCornerShape(12.dp),
            ),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = White,
            contentColor = Charcoal.copy(0.85f)
        ),
        elevation = ButtonDefaults.elevation(0.dp),
        onClick = onClick
    ) {
        Spacer(modifier = Modifier.width(6.dp))
        Text(text = label, style = MaterialTheme.typography.button)
        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = ""
            )
        }
    }
}