package com.samples.itis_android_inception_22.utils

import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class PermissionsRequestHandler(
    private val activity: AppCompatActivity,
    var singlePermissionCallback: ((Boolean) -> Unit)? = null,
) {

    private val requestSinglePermissionLauncher = activity.registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        singlePermissionCallback?.invoke(isGranted)
    }

    fun requestSinglePermission(permission: String) {
        requestSinglePermissionLauncher.launch(permission)
    }
}
