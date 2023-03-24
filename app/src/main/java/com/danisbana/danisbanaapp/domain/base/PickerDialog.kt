package com.danisbana.danisbanaapp.domain.base

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.danisbana.danisbanaapp.presentation.theme.Red
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PickerDialog(
    dialogState: BaseDialogState,
    action: (Uri) -> Unit = {},
) {
    val context = LocalContext.current
    val values = ContentValues()
    var uri: Uri = Uri.EMPTY
    context.contentResolver.insert(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values
    )?.let { uri = it }

    if (dialogState.isShowing) {
        val permission = rememberPermissionState(
            permission = android.Manifest.permission.CAMERA,
            onPermissionResult = { dialogState.hide() }
        )

        val launcherGallery = rememberLauncherForActivityResult(
            contract = GetMediaActivityResultContract(), onResult = {
                if(it!=null) action.invoke(it)
            }
        )

        val launcherCamera = rememberLauncherForActivityResult(
            contract = GetCameraActivityResultContract(),
            onResult = { action.invoke(uri) })
        SideEffect {
            if (permission.status.isGranted.not())
                permission.launchPermissionRequest()
        }
        Dialog(
            onDismissRequest = {
                dialogState.isShowing = false
            },
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 32.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Card(
                        modifier = Modifier.padding(horizontal = 15.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            TextButton(onClick = {
                                launcherGallery.launch("image/*")
                            }) {
                                Text(text = "Galeri ")
                            }
                            Divider()
                            TextButton(onClick = {
                                launcherCamera.launch(uri)
                            }) {
                                Text(text = "Kamera")
                            }
                            Divider()
                            Spacer(modifier = Modifier.height(15.dp))
                            TextButton(onClick = {
                                dialogState.isShowing = false
                            }) {
                                Text(text = "Vazgeç", color = Red.copy(alpha = 0.4f))
                            }
                        }

                    }
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        )
    }
}

class GetMediaActivityResultContract : ActivityResultContracts.GetContent() {
    override fun createIntent(context: Context, input: String): Intent {
        super.createIntent(context, input)
        return Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    }
}

class GetCameraActivityResultContract : ActivityResultContracts.TakePicture() {
    override fun createIntent(context: Context, input: Uri): Intent {
        super.createIntent(context, input)
        val intent =  Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, input)
        return intent
    }
}