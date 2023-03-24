package com.danisbana.danisbanaapp.presentation.screen.auth.forgot

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.danisbana.danisbanaapp.R
import com.danisbana.danisbanaapp.core.extension.imeExtra
import com.danisbana.danisbanaapp.domain.base.BaseScaffold
import com.danisbana.danisbanaapp.presentation.components.*
import com.danisbana.danisbanaapp.presentation.theme.AppDimens
import com.danisbana.danisbanaapp.presentation.theme.DanisBanaAppTheme
import com.danisbana.danisbanaapp.presentation.theme.LightSeaGreen

@Composable
fun ForgotPasswordScreen(
    state: ForgotPasswordState = ForgotPasswordState(),
    actions: ForgotPasswordActions = ForgotPasswordActions()
) {
    val scrollableState = rememberScrollState()
    BaseScaffold(
        modifier = Modifier.navigationBarsPadding().statusBarsPadding(),
        snackBarHostState = state.snackBarHostState,
        loadingState = state.pageLoading,
        topBar = {
            MAppBar(
                onBackAction = actions.onBackAction
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .statusBarsPadding()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .verticalScroll(scrollableState, reverseScrolling = true)
                    .imeExtra(),
                verticalArrangement = Arrangement.spacedBy(16.dp,Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.forgot_password),
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = AppDimens.wallSpace),
                )
                Text(
                    text = "Şifrenizi yeniden oluşturabilmeniz için kayıtlı e-posta adresinize şifre sıfırlama postası gelecektir",
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = AppDimens.wallSpace + 10.dp),
                )
                Spacer(modifier = Modifier.height(4.dp))
                MEmailTextField(
                    Modifier.padding(horizontal = AppDimens.wallSpace),
                    value = state.email,
                    onValueChange = { state.email = it },
                    label = stringResource(R.string.email),
                    error = state.formState.emailError
                )
                Spacer(modifier = Modifier.height(8.dp))
                PrimaryButton(
                    modifier = Modifier.padding(horizontal = AppDimens.wallSpace),
                    label = stringResource(R.string.send),
                    onClick = actions.submit,
                    buttonState = state.buttonEnabled
                )
                Spacer(modifier = Modifier.height(72.dp))
            }
        }
    }
}

@Composable
@Preview(name = "ForgotPassword")
private fun ForgotPasswordScreenPreview() {
    DanisBanaAppTheme {
        ForgotPasswordScreen()
    }
}

