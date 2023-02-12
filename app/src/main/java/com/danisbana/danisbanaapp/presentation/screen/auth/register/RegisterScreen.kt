package com.danisbana.danisbanaapp.presentation.screen.auth.register

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.danisbana.danisbanaapp.R
import com.danisbana.danisbanaapp.presentation.components.*
import com.danisbana.danisbanaapp.presentation.navigation.Screen
import com.danisbana.danisbanaapp.presentation.theme.AppDimens
import com.danisbana.danisbanaapp.presentation.theme.DanisBanaAppTheme
import com.danisbana.danisbanaapp.presentation.theme.LightSeaGreen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.stateFlow.collectAsState(RegisterState())
    val scrollableState = rememberScrollState()

    LaunchedEffect(true) {
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
                LottieView(
                    modifier  = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.6f),
                    res = R.raw.lottie_anim_profile_setup,
                    replay = true,
                    speed = 0.2f
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.register_form),
                        style = MaterialTheme.typography.h2,
                        modifier = Modifier.padding(horizontal = AppDimens.wallSpace+10.dp)
                    )
                    MTextField(
                        Modifier.padding(horizontal = AppDimens.wallSpace),
                        value = uiState.fullName,
                        onValueChange = { uiState.fullName = it },
                        label = stringResource(R.string.fullname)
                    )
                    MTextField(
                        Modifier.padding(horizontal = AppDimens.wallSpace),
                        value = uiState.email,
                        onValueChange = { uiState.email = it },
                        label = stringResource(id = R.string.email)
                    )
                    MPasswordTextField(
                        Modifier.padding(horizontal = AppDimens.wallSpace),
                        value = uiState.password,
                        onValueChange = { uiState.password = it },
                        label = stringResource(id = R.string.password)
                    )
                    MPasswordTextField(
                        Modifier.padding(horizontal = AppDimens.wallSpace),
                        value = uiState.passwordConfirm,
                        onValueChange = { uiState.passwordConfirm = it },
                        label = stringResource(R.string.password_again)
                    )
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
                    PrimaryButton(
                        modifier = Modifier.padding(horizontal = AppDimens.wallSpace),
                        label = stringResource(id = R.string.register)
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
                    onClick = viewModel::routeLogin
                )
            }
        }
    }
}

@Composable
@Preview(name = "Register")
private fun RegisterScreenPreview() {
    val viewModel = remember {
        RegisterViewModel(SavedStateHandle())
    }
    DanisBanaAppTheme {
        RegisterScreen(viewModel)
    }
}

