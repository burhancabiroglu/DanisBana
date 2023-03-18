package com.danisbana.danisbanaapp.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.danisbana.danisbanaapp.presentation.components.LocalBackPressedDispatcher
import com.danisbana.danisbanaapp.presentation.navigation.SetupNavGraph
import com.danisbana.danisbanaapp.presentation.theme.DanisBanaAppTheme
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Firebase.messaging.isAutoInitEnabled = true
        FirebaseMessaging.getInstance().isAutoInitEnabled = true

        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContent {
            DanisBanaAppTheme {
                val navController = rememberNavController()
                CompositionLocalProvider(
                    LocalBackPressedDispatcher provides this@MainActivity.onBackPressedDispatcher
                ) {
                    SetupNavGraph(navController = navController)
                }
            }
        }
    }
}