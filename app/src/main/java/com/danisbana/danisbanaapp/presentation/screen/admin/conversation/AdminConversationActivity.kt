package com.danisbana.danisbanaapp.presentation.screen.admin.conversation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.viewModels
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import com.danisbana.danisbanaapp.presentation.theme.DanisBanaAppTheme
import com.danisbana.danisbanaapp.presentation.theme.White
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminConversationActivity: ComponentActivity() {

    private val viewModel: AdminConversationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        val args = intent.extras?.getParcelable<AdminConversationArgs>(ADMIN_CONVERSATION_ARGS_KEY)
        setContent {
            val actions = rememberAdminConversationActions(viewModel)
            val uiState by viewModel.stateFlow.collectAsState()
            val uiController = rememberSystemUiController()
            viewModel.setStateArgs(args)
            actions.onBackClick = ::finish
            actions.confirmAction = {
                setResult(Activity.RESULT_OK)
                viewModel.confirmMessage(::finish)
            }
            actions.rejectAction = {
                setResult(Activity.RESULT_OK)
                viewModel.rejectMessage(::finish)
            }
            DanisBanaAppTheme {
                AdminConversationScreen(actions = actions, state = uiState)
            }
            DisposableEffect(true) {
                uiController.setStatusBarColor(White)
                onDispose {  }
            }
        }
    }

    companion object {
        const val ADMIN_CONVERSATION_ARGS_KEY = "ADMIN_CONVERSATION_ARGS_KEY"
        fun launch (launcher: ManagedActivityResultLauncher<Intent, ActivityResult>, context: Context?, args: AdminConversationArgs) {
            val intent = Intent(context, AdminConversationActivity::class.java)
            val extras = Bundle()
            extras.putParcelable(ADMIN_CONVERSATION_ARGS_KEY,args)
            intent.putExtras(extras)
            launcher.launch(intent)
        }
    }
}