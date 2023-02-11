package com.danisbana.danisbanaapp.presentation.screen.auth.register

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.danisbana.danisbanaapp.R
import com.danisbana.danisbanaapp.presentation.components.*
import com.danisbana.danisbanaapp.presentation.navigation.Screen
import com.danisbana.danisbanaapp.presentation.theme.AppDimens
import com.danisbana.danisbanaapp.presentation.theme.LightSeaGreen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.stateFlow.collectAsState(RegisterState())
    val scrollableState = rememberScrollState()

    LaunchedEffect(key1 = Unit) {
        viewModel.navChannel.collectLatest {
            when(it){
                is RegisterNavChannel.RouteLogin -> navController.navigate(Screen.Login.route)
            }
        }
    }
    return Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(scrollableState),
        ){
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                   Text(
                       text = stringResource(id = R.string.app_name),
                       style = MaterialTheme.typography.h1,
                       color = MaterialTheme.colors.primary
                   )
                }
                LottieView(
                    modifier  = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.4f),
                    res = R.raw.lottie_anim_profile_setup,
                    replay = true,
                    speed = 0.2f
                )
                Text(
                    text = "Kayıt Formu",
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.padding(horizontal = AppDimens.wallSpace+10.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                MTextField(
                    Modifier.padding(horizontal = AppDimens.wallSpace),
                    value = uiState.fullName,
                    onValueChange = { uiState.fullName = it },
                    label = "Ad Soyad"
                )
                Spacer(modifier = Modifier.height(16.dp))
                MTextField(
                    Modifier.padding(horizontal = AppDimens.wallSpace),
                    value = uiState.email,
                    onValueChange = { uiState.email = it },
                    label = "E-Posta"
                )
                Spacer(modifier = Modifier.height(16.dp))
                MPasswordTextField(
                    Modifier.padding(horizontal = AppDimens.wallSpace),
                    value = uiState.password,
                    onValueChange = { uiState.password = it },
                    label = "Parola"
                )
                Spacer(modifier = Modifier.height(16.dp))
                MPasswordTextField(
                    Modifier.padding(horizontal = AppDimens.wallSpace),
                    value = uiState.passwordConfirm,
                    onValueChange = { uiState.passwordConfirm = it },
                    label = "Parola(Tekrar)"
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    Modifier.padding(horizontal = AppDimens.wallSpace),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = uiState.isPolicyChecked,
                        onCheckedChange = {}
                    )
                    Text(
                        text = "Lorem ipsum",
                        style = MaterialTheme.typography.overline
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                PrimaryButton(
                    modifier = Modifier.padding(horizontal = AppDimens.wallSpace),
                    label = "Kayıt Ol"
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(bottom = AppDimens.s22dp),
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
                        onClick = viewModel::routeLogin
                    )
                }
            }

        }
    }
}

@Composable
@Preview(name = "Register")
private fun RegisterScreenPreview() {
    RegisterScreen()
}

