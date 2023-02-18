package com.danisbana.danisbanaapp.presentation.screen.home.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danisbana.danisbanaapp.R
import com.danisbana.danisbanaapp.presentation.components.MAppBar
import com.danisbana.danisbanaapp.presentation.components.WhiteButton
import com.danisbana.danisbanaapp.presentation.screen.home.profile.components.PictureWheel
import com.danisbana.danisbanaapp.presentation.screen.home.profile.components.SummaryTable
import com.danisbana.danisbanaapp.presentation.theme.AppDimens
import com.danisbana.danisbanaapp.presentation.theme.DanisBanaAppTheme
import com.danisbana.danisbanaapp.presentation.theme.White

@Composable
fun ProfileScreen(
    state: ProfileState = ProfileState(),
    actions: ProfileActions = ProfileActions()
) {
    val scrollableState = rememberScrollState()
    return Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        backgroundColor = White,
        topBar = { MAppBar(
            title = stringResource(id = R.string.profile),
            logoutEnabled = true
        ) }
    ) {
       Column(
           modifier = Modifier
               .padding(it)
               .verticalScroll(scrollableState)
       ) {
           PictureWheel(
               pictureUrl = state.appUser?.firebaseUser?.photoUrl
           )
           Text(
               state.appUser?.firebaseUser?.displayName.toString(),
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(vertical = AppDimens.p20.dp),
               textAlign = TextAlign.Center
           )
           SummaryTable(
               modifier = Modifier.padding(horizontal = AppDimens.wallSpace)
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

