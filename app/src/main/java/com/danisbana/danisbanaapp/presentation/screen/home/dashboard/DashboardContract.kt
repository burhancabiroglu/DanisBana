package com.danisbana.danisbanaapp.presentation.screen.home.dashboard

import androidx.compose.material.SnackbarHostState
import com.danisbana.danisbanaapp.domain.base.BaseDialogState
import com.danisbana.danisbanaapp.domain.base.LoadingState
import com.google.android.gms.ads.rewarded.RewardItem

class DashboardState(
    val snackBarHostState: SnackbarHostState = SnackbarHostState()
) {
    var pointDialogState = BaseDialogState()
    var loadingState = LoadingState(false)
}

data class DashboardActions(
    var routeConsultant: () -> Unit = {},
    var loadingAction: (Boolean) -> Unit = {},
    var adsSuccess: (RewardItem) -> Unit = {}
)

