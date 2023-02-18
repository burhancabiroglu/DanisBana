package com.danisbana.danisbanaapp.presentation.screen.auth.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.danisbana.danisbanaapp.R
import com.danisbana.danisbanaapp.core.extension.imeExtra
import com.danisbana.danisbanaapp.presentation.components.*
import com.danisbana.danisbanaapp.presentation.components.indicator.PageLoading
import com.danisbana.danisbanaapp.presentation.theme.AppDimens
import com.danisbana.danisbanaapp.presentation.theme.DanisBanaAppTheme
import com.danisbana.danisbanaapp.presentation.theme.LightSeaGreen
import com.danisbana.danisbanaapp.presentation.theme.White

@Composable
fun LoginScreen(
    state: LoginState = LoginState(),
    actions: LoginActions = LoginActions(),
) {
    val scrollableState = rememberScrollState()

    return Surface(
        modifier = Modifier.fillMaxSize(),
        color = White
    ) {
        PageLoading(state.pageLoading)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .statusBarsPadding()
        ) {
            LottieView(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.4f)
                    .zIndex(1f),
                res = R.raw.lottie_anim_gray_seagulls,
                replay = true,
                speed = 0.4f
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = AppDimens.y10dp)
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.dont_have_an_account),
                    style = MaterialTheme.typography.body2,
                )
                MTextButton(
                    text = stringResource(R.string.register),
                    textStyle = MaterialTheme.typography.h3,
                    textColor = LightSeaGreen,
                    onClick = actions.routeRegister
                )
            }
            Column(
                modifier = Modifier
                    .verticalScroll(scrollableState, reverseScrolling = true)
                    .imeExtra()
            ) {
                Spacer(modifier = Modifier.height(92.dp))
                LottieView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.4f)
                        .zIndex(0.9f),
                    res = R.raw.lottie_anim_breathing_lotus,
                    replay = false,
                    speed = 3f
                )
                Text(
                    text = stringResource(R.string.welcome),
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier.padding(horizontal = AppDimens.wallSpace + 10.dp),
                )
                Spacer(modifier = Modifier.height(16.dp))
                MEmailTextField(
                    Modifier.padding(horizontal = AppDimens.wallSpace),
                    value = state.email,
                    onValueChange = { state.email = it },
                    label = stringResource(R.string.email)
                )
                Spacer(modifier = Modifier.height(16.dp))
                MPasswordTextField(
                    Modifier.padding(horizontal = AppDimens.wallSpace),
                    value = state.password,
                    onValueChange = { state.password = it },
                    label = stringResource(R.string.password)
                )
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = AppDimens.wallSpace),
                    horizontalArrangement = Arrangement.End
                ) {
                    MTextButton(text = stringResource(R.string.forgot_password))
                }
                Spacer(modifier = Modifier.height(32.dp))
                PrimaryButton(
                    modifier = Modifier.padding(horizontal = AppDimens.wallSpace),
                    label = stringResource(R.string.login),
                    onClick = actions.tryLogin,
                    buttonState = state.buttonEnabled
                )
            }
        }
    }
}

@Composable
@Preview(name = "Login")
internal fun LoginScreenPreview() {
    DanisBanaAppTheme {
        LoginScreen()
    }
}

