package com.samples.itis_android_inception_22.presentation.fragments.permissionRequestScrren

import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.samples.itis_android_inception_22.R
import com.samples.itis_android_inception_22.databinding.FragmentRequestPermissionBinding
import com.samples.itis_android_inception_22.presentation.base.BaseFragment

class PermissionRequestFragment : BaseFragment(R.layout.fragment_request_permission) {

    private val viewBinding: FragmentRequestPermissionBinding by viewBinding(FragmentRequestPermissionBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding) {
            val permission = android.Manifest.permission.CAMERA
            requestPermissionOldWayBtn.setOnClickListener {
                if (ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED
                ) {
                    // Разрешение получено
                    permissionGranted(permission)
                } else {
                    requestPermissions(arrayOf(android.Manifest.permission.CAMERA), REQUEST_CODE_CAMERA)
                }
            }
            requestPermissionNewWayBtn.setOnClickListener {

            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_CAMERA -> {
                val permission = if (permissions.isNotEmpty()) permissions.first() else return
                if (grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                    permissionGranted(permission)
                } else {
                    Toast.makeText(requireContext(), "Разрешение отклонено", Toast.LENGTH_SHORT).show()
                    if (shouldShowRequestPermissionRationale(permission)) {
                        // Пользователь отклонин единожды, у нас есть возможность объяснить, почему нам нужно разрешение

                    } else {
                        // Пользователь отклонил запрос разрешения многократно. Запросить разрешение снова мы не можем
                        // Можем показать сообщение с кнопкой перехода в настройки
                        showOpenSettingsAlert()
                    }
                }
            }
            else -> {
                // Do nothing
            }
        }
    }

    private fun permissionGranted(permission: String) {
        Toast.makeText(requireContext(), "Разрешение $permission получено", Toast.LENGTH_SHORT).show()
    }

    private fun showOpenSettingsAlert() {
        val settingsIntent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", requireContext().packageName, null)
        )
        if (requireActivity().packageManager.resolveActivity(
                settingsIntent, PackageManager.MATCH_DEFAULT_ONLY
            ) == null
        ) {

        } else {
            AlertDialog.Builder(requireContext())
                .setTitle("Вы отклонили разрешение на использование камеры")
                .setMessage("Это разрешение нужно для того, чтобы сделать действие 1, 2, 3. Может все-таки предоставите доступ?")
                .setPositiveButton("Перейти к настройкам") { _, _ ->
                    startActivity(settingsIntent)
                }
                .create()
                .show()
        }
    }

    companion object {
        private const val REQUEST_CODE_CAMERA = 12101
        private const val REQUEST_CODE_CAMERA_ACTIVITY_RESULT = 12202

        const val PERMISSION_REQUEST_FRAGMENT_TAG = "PERMISSION_REQUEST_FRAGMENT_TAG"

        fun getInstance() = PermissionRequestFragment()
    }
}