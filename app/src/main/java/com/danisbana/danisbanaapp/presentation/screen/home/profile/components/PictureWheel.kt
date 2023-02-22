package com.danisbana.danisbanaapp.presentation.screen.home.profile.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import com.danisbana.danisbanaapp.presentation.theme.White
import com.danisbana.danisbanaapp.R
import com.danisbana.danisbanaapp.presentation.theme.CadetBlue
import com.danisbana.danisbanaapp.presentation.theme.Charcoal
import com.danisbana.danisbanaapp.presentation.theme.Marigold

@Composable
fun PictureWheel(
    modifier: Modifier = Modifier,
    pictureUrl: Uri? = null,
    action: () -> Unit = {}
) {
    val conf = LocalConfiguration.current
    val screenWidth = conf.screenWidthDp
    val screenHeight = conf.screenHeightDp
    return Box(
        Modifier
            .fillMaxWidth()
            .padding(top = (screenHeight * 0.025).dp),
        contentAlignment = Alignment.Center
    ){
        Card(
            shape = CircleShape
        ) {
            Box(
                modifier = Modifier
                    .width((screenWidth * 0.45).dp)
                    .aspectRatio(1f)
                    .background(White, CircleShape)
                    .padding(5.dp),
                contentAlignment = Alignment.BottomCenter
            ){
                if(pictureUrl == null){
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.ic_user_no_picture),
                        contentDescription = ""
                    )
                }
                else {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = rememberAsyncImagePainter(model = pictureUrl),
                        contentDescription = ""
                    )
                }
            }
        }
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "",
            tint = White,
            modifier = modifier
                .offset(x = 60.dp, y = (60).dp)
                .background(Marigold, CircleShape)
                .padding(8.dp)
                .clickable(onClick = action)
        )
    }
}

@Preview(name = "PictureWheel")
@Composable
private fun PreviewPictureWheel() {
    PictureWheel()
}