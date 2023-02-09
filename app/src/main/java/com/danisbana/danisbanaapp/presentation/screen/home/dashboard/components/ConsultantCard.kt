package com.danisbana.danisbanaapp.presentation.screen.home.dashboard.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danisbana.danisbanaapp.R
import com.danisbana.danisbanaapp.presentation.components.BadgeButton
import com.danisbana.danisbanaapp.presentation.components.LottieView
import com.danisbana.danisbanaapp.presentation.theme.AppDimens
import com.danisbana.danisbanaapp.presentation.theme.CadetBlue
import com.danisbana.danisbanaapp.presentation.theme.White

@Composable
fun ConsultantCard(
    modifier: Modifier = Modifier
) {
    Card(
        modifier.padding(AppDimens.wallSpace).shadow(
            elevation = 3.dp,
            clip = false,
            spotColor = CadetBlue,
            ambientColor = CadetBlue,
            shape = RoundedCornerShape(12.dp),
        ),
        elevation = 0.dp,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = White
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LottieView(
                    res = R.raw.lottie_anim_psychotherapy,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.2f),
                    replay = true
                )
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.h2
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(id = R.string.home_page_description),
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                BadgeButton(
                    label = "Başlayın"
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Preview(name = "ConsultantCard")
@Composable
private fun PreviewConsultantCard() {
    ConsultantCard()
}