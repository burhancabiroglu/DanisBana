package com.danisbana.danisbanaapp.presentation.screen.home.consultant

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.danisbana.danisbanaapp.R
import com.danisbana.danisbanaapp.core.extension.imeExtra
import com.danisbana.danisbanaapp.domain.base.BaseScaffold
import com.danisbana.danisbanaapp.presentation.components.MAppBar
import com.danisbana.danisbanaapp.presentation.components.MTextFieldVariant
import com.danisbana.danisbanaapp.presentation.components.PrimaryButton
import com.danisbana.danisbanaapp.presentation.theme.AppDimens
import com.danisbana.danisbanaapp.presentation.theme.DanisBanaAppTheme
import com.danisbana.danisbanaapp.presentation.theme.Transparent

@Composable
fun ConsultantScreen(
    state: ConsultantState = ConsultantState(),
    actions: ConsultantActions = ConsultantActions(),
    navController: NavHostController = rememberNavController()
) {
    val scrollState = rememberScrollState()
    return BaseScaffold(
        modifier = Modifier.statusBarsPadding().navigationBarsPadding(),
        loadingState = state.pageLoading,
        topBar = {
            MAppBar(
                title = stringResource(R.string.new_consultant_note),
                navIconEnabled = true,
                navHostController = navController
            )
        }
    ){
        Image(
            painter = painterResource(id = R.drawable.background_paper),
            contentDescription = "background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Transparent)
                .padding(horizontal = AppDimens.wallSpace)
                .imeExtra()
                .verticalScroll(scrollState, reverseScrolling = true),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            Spacer(modifier = Modifier.height(12.dp))
            MTextFieldVariant(
                value =  state.noteTitle,
                onValueChange = { state.noteTitle = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = stringResource(R.string.note_header_dot)
            )
            MTextFieldVariant(
                value =  state.noteDetail,
                onValueChange = { state.noteDetail = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 290.dp),
                placeholder = stringResource(R.string.note_detail_dot)
            )
            Spacer(modifier = Modifier.weight(1f))
            PrimaryButton(
                label = stringResource(R.string.send),
                buttonState = state.buttonState,
                onClick = actions.onSubmit
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
@Preview(name = "Consultant")
private fun ConsultantScreenPreview() {
    DanisBanaAppTheme {
        ConsultantScreen()
    }
}

