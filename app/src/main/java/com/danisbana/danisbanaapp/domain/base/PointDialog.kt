package com.danisbana.danisbanaapp.domain.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.danisbana.danisbanaapp.R
import com.danisbana.danisbanaapp.presentation.components.LottieView
import com.danisbana.danisbanaapp.presentation.theme.DanisBanaAppTheme
import com.danisbana.danisbanaapp.presentation.theme.White

@Composable
fun PointDialog(
    modifier: Modifier = Modifier,
    dialogState: BaseDialogState = BaseDialogState(),
    action: () -> Unit = {},
) {
    val gradient =  Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colors.primary,
            MaterialTheme.colors.primary.copy(alpha = 0.6f)
        )
    )
    if (dialogState.isShowing) {
        Dialog(
            onDismissRequest = dialogState::hide
        ) {
            Box(
                modifier = modifier
                    .padding(top = 8.dp, bottom = 24.dp, end = 28.dp, start = 28.dp)
                    .background(White, RoundedCornerShape(12.dp))
                    .padding(top = 8.dp, bottom = 24.dp, end = 28.dp, start = 28.dp)
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier.fillMaxWidth().offset(x = 18.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(onClick = dialogState::hide) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "",
                                modifier = Modifier.alpha(0.5f)
                            )
                        }
                    }
                    Box(modifier = Modifier.size(120.dp)) {
                        LottieView(res = R.raw.lottie_anim_warning)
                    }
                    Text(text = "Yetersiz Lotus Puan", style = MaterialTheme.typography.h2)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = "Uzmanlarımıza soru sorup danışabilmek için daha fazla puana ihtiyacınız var.", textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        modifier = Modifier.background(gradient, RoundedCornerShape(60.dp)),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0x00FCC41B)
                        ),
                        elevation = ButtonDefaults.elevation(0.dp,0.dp),
                        shape = RoundedCornerShape(60.dp),
                        contentPadding = PaddingValues(top = 1.dp, bottom = 1.dp, start = 16.dp, end = 32.dp),
                        onClick = action
                    ) {
                        Row(
                            modifier = Modifier.height(IntrinsicSize.Min),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(modifier = Modifier
                                .fillMaxHeight()
                                .aspectRatio(1.1f)) {
                                LottieView(res = R.raw.lottie_anim_ads, replay = true, speed = 0.6f)
                            }
                            Text(text = "Puan Kazan", color = White)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PointDialogPrev() {
    DanisBanaAppTheme {
        PointDialog()
    }
}