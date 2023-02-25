package com.danisbana.danisbanaapp.presentation.screen.response

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danisbana.danisbanaapp.R
import com.danisbana.danisbanaapp.presentation.components.LottieView
import com.danisbana.danisbanaapp.presentation.theme.DanisBanaAppTheme
import com.danisbana.danisbanaapp.presentation.theme.QueenBlue

@Composable
fun SuccessScreen(
    action: () -> Unit = {}
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .statusBarsPadding(),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(14.dp,Alignment.CenterVertically)
            ) {
                LottieView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.4f),
                    res = R.raw.lottie_anim_email_send,
                    replay = true,
                    speed = 0.8f
                )
                Text(
                    text = "Doğrulama E-Postası Gönderildi",
                    style = MaterialTheme.typography.h2,
                )
                Text(
                    text = "Hesabınızı kullanabilmek için lütfen e-postanıza gönderilen doğrulama postasını onaylayın.",
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedButton(
                    onClick =  action,
                    shape = RoundedCornerShape(18.dp),
                    border = BorderStroke(1.dp,QueenBlue),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.login),
                        style = MaterialTheme.typography.h3
                    )
                }

            }
        }
    }
}




@Preview
@Composable
fun SuccessPagePreview() {
    DanisBanaAppTheme {
        SuccessScreen()
    }
}