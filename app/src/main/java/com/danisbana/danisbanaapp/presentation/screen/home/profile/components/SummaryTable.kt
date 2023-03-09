package com.danisbana.danisbanaapp.presentation.screen.home.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.danisbana.danisbanaapp.presentation.theme.CadetBlue
import com.danisbana.danisbanaapp.presentation.theme.White
import com.danisbana.danisbanaapp.R

@Composable
fun SummaryTable(
    modifier: Modifier = Modifier,
    point: Int? = null,
    totalMessages: Int? = null,
    acceptedMessages: Int? = null,
) {
    return Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Card(
            shape = RoundedCornerShape(12.dp),
            backgroundColor = White,
            modifier = Modifier.shadow(
                elevation = 3.dp,
                clip = false,
                spotColor = CadetBlue,
                ambientColor = CadetBlue,
                shape = RoundedCornerShape(12.dp),
            ),
            elevation = 0.dp
        ) {
            Box(
                Modifier.height(140.dp)
            ) {
                Row(
                    Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    SummaryColumn(
                        image = painterResource(id = R.drawable.ic_approved_messages),
                        text = "Onaylanan\nMesajlar",
                        value = acceptedMessages?.toString()?:"-"
                    )
                    VerticalDivider()
                    SummaryColumn(
                        image = painterResource(id = R.drawable.ic_all_messages),
                        text = "Toplam\nMesajlar",
                        value = totalMessages?.toString()?:"-"
                    )
                    VerticalDivider()
                    SummaryColumn(
                        image = painterResource(id = R.drawable.ic_trophy),
                        text = "Lotus\nPuan",
                        value = point?.toString()?:"-"
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
    icon: ImageVector? = null,
    image: Painter? = null,
    tint: Color = CadetBlue,
    value: String = ""

) {
    Column(
        modifier = Modifier.weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(icon!=null) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = tint,
                modifier = Modifier.size(30.dp)
            )
        }
        if(image!=null) {
            Image(
                painter = image,
                contentDescription = text,
                modifier = Modifier.size(32.dp)
            )
        }
        Spacer(modifier = Modifier.size(6.dp))
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption
        )
        Spacer(modifier = Modifier.size(3.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.h2
        )
    }
}