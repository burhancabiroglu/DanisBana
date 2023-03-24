package com.danisbana.danisbanaapp.presentation.screen.home.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.danisbana.danisbanaapp.R
import com.danisbana.danisbanaapp.core.util.AdmobHelper
import com.danisbana.danisbanaapp.domain.base.BaseScaffold
import com.danisbana.danisbanaapp.domain.base.PointDialog
import com.danisbana.danisbanaapp.presentation.components.MAppBar
import com.danisbana.danisbanaapp.presentation.screen.home.dashboard.components.ConsultantCard
import com.danisbana.danisbanaapp.presentation.theme.DanisBanaAppTheme

@Composable
fun DashboardScreen(
    state: DashboardState = DashboardState(),
    actions: DashboardActions = DashboardActions(),
) {
    val context = LocalContext.current
    val admobHelper = AdmobHelper(context)
    admobHelper.setOnAdLoading(actions.loadingAction)
    admobHelper.setOnSuccess(actions.adsSuccess)

    BaseScaffold(
        topBar = {
            MAppBar(title = stringResource(R.string.home_page))
        },
        loadingState = state.loadingState,
        snackBarHostState = state.snackBarHostState
    ) {
        Column {
            ConsultantCard(onClick = actions.routeConsultant)
        }
        PointDialog(
            dialogState = state.pointDialogState,
            action = admobHelper::loadRewardedAds
        )
    }
}

@Composable
@Preview(name = "Dashboard")
private fun DashboardScreenPreview() {
    DanisBanaAppTheme {
        DashboardScreen()
    }
}

