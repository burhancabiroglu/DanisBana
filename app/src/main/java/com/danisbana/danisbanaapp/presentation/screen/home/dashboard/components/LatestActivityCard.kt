package com.danisbana.danisbanaapp.presentation.screen.home.dashboard.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danisbana.danisbanaapp.R
import com.danisbana.danisbanaapp.presentation.screen.home.messages.components.MessageListItem
import com.danisbana.danisbanaapp.presentation.theme.AppDimens
import com.danisbana.danisbanaapp.presentation.theme.CadetBlue
import com.danisbana.danisbanaapp.presentation.theme.White

@Composable
fun LatestActivityCard(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.padding(horizontal = AppDimens.wallSpace, vertical = 5.dp)
    ) {
        Text(
            text = stringResource(id = R.string.home_page_activites),
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(start = 8.dp, bottom = 5.dp)
        )
        Card(
            modifier
                .shadow(
                    elevation = 3.dp,
                    clip = false,
                    spotColor = CadetBlue,
                    ambientColor = CadetBlue,
                    shape = RoundedCornerShape(12.dp),
                )
                .padding(vertical = 2.dp),
            elevation = 0.dp,
            shape = RoundedCornerShape(16.dp),
            backgroundColor = White
        ) {
            MessageListItem(
                dividerVisibility = false
            )
        }
    }
}

@Preview(name = "LatestActivityCard")
@Composable
private fun PreviewLatestActivityCard() {
    LatestActivityCard()
}