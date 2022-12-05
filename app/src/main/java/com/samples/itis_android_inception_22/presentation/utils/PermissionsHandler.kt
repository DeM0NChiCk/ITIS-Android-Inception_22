package com.samples.itis_android_inception_22.presentation.utils

import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class PermissionsHandler(
    activity: AppCompatActivity,
    onPermissionGranted: () -> Unit
) {

    private val singlePermissionContract =
        activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                onPermissionGranted()
            }
        }

    fun requestSinglePermission(permission: String) {
        singlePermissionContract.launch(permission)
    }
}