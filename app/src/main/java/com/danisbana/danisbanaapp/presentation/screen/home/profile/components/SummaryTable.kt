package com.danisbana.danisbanaapp.presentation.screen.home.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danisbana.danisbanaapp.presentation.theme.CadetBlue

@Composable
fun SummaryTable(
    modifier: Modifier = Modifier
) {
    return Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Card(
            shape = RoundedCornerShape(16.dp)
        ) {
            Box(
                Modifier.height(140.dp)
            ) {
                Row(
                    Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    SummaryColumn(
                        icon = Icons.Default.Email,
                        text = "Onaylanan\nMesajlar"
                    )
                    VerticalDivider()
                    SummaryColumn(
                        icon = Icons.Default.Email,
                        text = "Onaylanmayan\nMesajlar"
                    )
                    VerticalDivider()
                    SummaryColumn(
                        icon = Icons.Default.Email,
                        text = "Lotus\nPuan"
                    )
                }
            }
        }
    }
}

@Composable
private fun VerticalDivider() {
    Divider(
        modifier = Modifier
            .fillMaxHeight()
            .width(0.8.dp)
            .padding(vertical = 16.dp)
    )
}

@Composable
private fun RowScope.SummaryColumn(
    text: String,
    icon: ImageVector,
    tint: Color = CadetBlue

) {
    Column(
        modifier = Modifier.weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = tint
        )
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 12.sp)
        )
        Text(text = "12")
    }
}


@Preview(name = "SummaryTable")
@Composable
private fun PreviewSummaryTable() {
    SummaryTable()
}