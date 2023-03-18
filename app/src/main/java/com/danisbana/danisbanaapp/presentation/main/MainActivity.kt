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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
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