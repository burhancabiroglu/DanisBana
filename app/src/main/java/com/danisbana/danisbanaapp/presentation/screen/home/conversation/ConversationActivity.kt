package com.danisbana.danisbanaapp.presentation.screen.home.conversation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import com.danisbana.danisbanaapp.presentation.theme.DanisBanaAppTheme
import com.danisbana.danisbanaapp.presentation.theme.White
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class ConversationActivity: ComponentActivity() {

    private val viewModel: ConversationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        val args = intent.getParcelableExtra<ConversationArgs>(CONVERSATION_ARGS_KEY)
        setContent {
            val actions = rememberConversationActions(viewModel)
            val uiState by viewModel.stateFlow.collectAsState()
            val uiController = rememberSystemUiController()
            viewModel.setStateArgs(args)
            actions.onBackClick = ::finish
            DanisBanaAppTheme {
                ConversationScreen(actions = actions, state = uiState)
            }
            DisposableEffect(true) {
                uiController.setStatusBarColor(White)
                onDispose {  }
            }
        }
    }

    companion object {
        private const val CONVERSATION_ARGS_KEY = "CONVERSATION_ARGS_KEY"
        fun launch (context: Context?, args: ConversationArgs) {
            val intent = Intent(context,ConversationActivity::class.java)
            intent.putExtra(CONVERSATION_ARGS_KEY,args)
            context?.startActivity(intent)
        }
    }
}