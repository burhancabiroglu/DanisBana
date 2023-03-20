package com.danisbana.danisbanaapp.presentation.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.content.ContextCompat
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
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { checkPermissions() }
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

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun checkPermissions() {
        var permissionsAccepted: Boolean = true
        requiredPermissions.forEach {
            val innerPermission = ContextCompat.checkSelfPermission(this,it) != PackageManager.PERMISSION_GRANTED
            if(innerPermission) requestPermissionLauncher.launch(it)
            permissionsAccepted = permissionsAccepted && innerPermission.not()
        }
        if(permissionsAccepted) return
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if(isGranted) return@registerForActivityResult
        }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private var requiredPermissions = arrayOf(
        Manifest.permission.POST_NOTIFICATIONS,
    )
}