package com.danisbana.danisbanaapp.presentation.screen.home.profile

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danisbana.danisbanaapp.R
import com.danisbana.danisbanaapp.domain.base.BaseScaffold
import com.danisbana.danisbanaapp.domain.base.rememberDialogState
import com.danisbana.danisbanaapp.presentation.components.MAppBar
import com.danisbana.danisbanaapp.presentation.components.WhiteButton
import com.danisbana.danisbanaapp.presentation.screen.home.profile.components.PictureWheel
import com.danisbana.danisbanaapp.presentation.screen.home.profile.components.SummaryTable
import com.danisbana.danisbanaapp.presentation.theme.AppDimens
import com.danisbana.danisbanaapp.presentation.theme.DanisBanaAppTheme

@Composable
fun ProfileScreen(
    state: ProfileState = ProfileState(),
    actions: ProfileActions = ProfileActions(),
) {
    val context = LocalContext.current
    val scrollableState = rememberScrollState()
    val dialogState = rememberDialogState()
    val pickerState = rememberDialogState()
    val pickerAction = fun(uri: Uri) {
        context.contentResolver.openInputStream(uri).use {
            val bytes = it?.readBytes() ?: return@use
            actions.updatePicture(bytes)
        }
    }
    return BaseScaffold(
        dialogAction = actions.logout,
        dialogState = dialogState,
        loadingState = state.pageLoading,
        pickerDialogState = pickerState,
        pickerAction = pickerAction,
        topBar = {
            MAppBar(
                title = stringResource(id = R.string.profile),
                logoutEnabled = true,
                logoutAction = dialogState::open,
            )
        }
    ) {
        Column(
            modifier = Modifier.verticalScroll(scrollableState)
        ) {
            PictureWheel(
                pictureUrl = state.appUser?.firebaseUser?.photoUrl,
                action = {
                    pickerState.open()
                }
            )
            Text(
                state.appUser?.firebaseUser?.displayName?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = AppDimens.p20.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h2
            )
            SummaryTable(
                modifier = Modifier.padding(horizontal = AppDimens.wallSpace),
                userInfo = state.appUser?.info
            )
            Column(
                modifier = Modifier.padding(AppDimens.wallSpace),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                WhiteButton(label = stringResource(id = R.string.profile_info))
                WhiteButton(label = stringResource(id = R.string.update_password))
                WhiteButton(label = stringResource(id = R.string.earn_point))
            }
        }
    }
}

@Composable
@Preview(name = "Profile")
private fun ProfileScreenPreview() {
    DanisBanaAppTheme {
        ProfileScreen()
    }
}

