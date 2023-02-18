package com.danisbana.danisbanaapp.presentation.screen.auth.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danisbana.danisbanaapp.R
import com.danisbana.danisbanaapp.core.extension.imeExtra
import com.danisbana.danisbanaapp.presentation.components.*
import com.danisbana.danisbanaapp.presentation.components.indicator.PageLoading
import com.danisbana.danisbanaapp.presentation.theme.*

@Composable
fun RegisterScreen(
    state: RegisterState = RegisterState(),
    actions: RegisterActions = RegisterActions()
) {
    val scrollableState = rememberScrollState()
    val textFieldModifier = Modifier.padding(horizontal = AppDimens.wallSpace)
    return Surface(
        modifier = Modifier.fillMaxSize(),
        color = White
    ) {
        PageLoading(state.pageLoading)
        Box(
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding()
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .navigationBarsPadding()
                    .verticalScroll(scrollableState, reverseScrolling = true)
                    .imeExtra()
            ) {
                LottieView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.6f),
                    res = R.raw.lottie_anim_profile_setup,
                    replay = true,
                    speed = 0.2f
                )
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = stringResource(R.string.register_form),
                        style = MaterialTheme.typography.h2,
                        modifier = Modifier.padding(horizontal = AppDimens.wallSpace + 10.dp)
                    )
                    MTextField(
                        textFieldModifier,
                        value = state.fullName,
                        onValueChange = { state.fullName = it },
                        label = stringResource(R.string.fullname),
                    )
                    MEmailTextField(
                        textFieldModifier,
                        value = state.email,
                        onValueChange = { state.email = it },
                        label = stringResource(id = R.string.email),
                        error = state.formState.emailError
                    )
                    MPasswordTextField(
                        textFieldModifier,
                        value = state.password,
                        onValueChange = { state.password = it },
                        label = stringResource(id = R.string.password),
                        error = state.formState.passwordError
                    )
                    MPasswordTextField(
                        textFieldModifier,
                        value = state.passwordConfirm,
                        onValueChange = { state.passwordConfirm = it },
                        label = stringResource(R.string.password_again),
                        error = state.formState.repeatedPasswordError
                    )
                    Row(
                        Modifier.padding(horizontal = AppDimens.wallSpace),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = state.isPolicyChecked,
                            onCheckedChange = actions.policyCheckAction
                        )
                        Text(
                            text = state.agreement?.label.toString(),
                            style = MaterialTheme.typography.overline
                        )
                    }
                    PrimaryButton(
                        modifier = Modifier.padding(horizontal = AppDimens.wallSpace),
                        label = stringResource(id = R.string.register),
                        onClick = actions.tryRegister,
                        buttonState = state.buttonEnabled
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = AppDimens.y10dp)
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.do_you_have_an_account),
                    style = MaterialTheme.typography.body2,
                )
                MTextButton(
                    text = stringResource(id = R.string.login),
                    textStyle = MaterialTheme.typography.h3,
                    textColor = LightSeaGreen,
                    onClick = actions.routeLogin
                )
            }
            SnackbarHost(
                modifier = Modifier.align(Alignment.Center),
                hostState = state.snackbarHostState
            ) {
                val bgColor =
                    if (it.actionLabel == "error")
                        Red.copy(alpha = 0.4f)
                    else
                        SuccessGreen.copy(alpha = 0.6f)

                val icon =
                    if(it.actionLabel == "error")
                        Icons.Default.Error
                    else
                        Icons.Default.Check
                Card(
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor = White,
                    modifier = Modifier
                        .height(120.dp)
                        .width(230.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(bgColor)
                            .fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier.padding(8.dp).fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(
                                4.dp,
                                Alignment.CenterVertically
                            ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = it.actionLabel,
                                tint = White
                            )
                            Text(text = it.message, color = White, textAlign = TextAlign.Center)
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun RegisterScreenPreview() {
    DanisBanaAppTheme {
        RegisterScreen()
    }
}