package com.danisbana.danisbanaapp.presentation.screen.home.consultant

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.danisbana.danisbanaapp.R
import com.danisbana.danisbanaapp.presentation.components.MAppBar
import com.danisbana.danisbanaapp.presentation.components.MTextFieldVariant
import com.danisbana.danisbanaapp.presentation.components.PrimaryButton
import com.danisbana.danisbanaapp.presentation.theme.AppDimens
import com.danisbana.danisbanaapp.presentation.theme.DanisBanaAppTheme
import com.danisbana.danisbanaapp.presentation.theme.Transparent

@Composable
fun ConsultantScreen(
    viewModel: ConsultantViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.stateFlow.collectAsState(ConsultantState())
    val scrollState = rememberScrollState()
    val buttonState by remember {
        derivedStateOf {
            uiState.noteDetail.text.isNotEmpty() && uiState.noteTitle.text.isNotEmpty()
        }
    }

    return Scaffold(
        topBar = {
            MAppBar(
                title = "Yeni Danışma Notu",
                navIconEnabled = true,
                navHostController = navController
            )
        }
    ) { paddingValues ->
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
                .padding(
                    horizontal = AppDimens.wallSpace,
                    vertical = paddingValues.calculateBottomPadding()
                )
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            Spacer(modifier = Modifier.height(12.dp))
            MTextFieldVariant(
                value =  uiState.noteTitle,
                onValueChange = { uiState.noteTitle = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = stringResource(R.string.note_header_dot)
            )
            MTextFieldVariant(
                value =  uiState.noteDetail,
                onValueChange = { uiState.noteDetail = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 290.dp),
                placeholder = stringResource(R.string.note_detail_dot)
            )
            Spacer(modifier = Modifier.weight(1f))
            PrimaryButton(label = stringResource(R.string.send), buttonState = buttonState)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
@Preview(name = "Consultant")
private fun ConsultantScreenPreview() {
    val viewModel = remember {
        ConsultantViewModel(SavedStateHandle())
    }
    DanisBanaAppTheme {
        ConsultantScreen(viewModel)
    }
}

