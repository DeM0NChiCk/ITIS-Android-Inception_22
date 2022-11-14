package com.samples.itis_android_inception_22.presentation

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.samples.itis_android_inception_22.R
import com.samples.itis_android_inception_22.databinding.ActivityMainBinding
import com.samples.itis_android_inception_22.presentation.base.BaseActivity
import com.samples.itis_android_inception_22.presentation.fragments.gridFragment.GridManagerFragment
import com.samples.itis_android_inception_22.presentation.fragments.permissionRequestScrren.PermissionRequestFragment

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val viewBinding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    override val fragmentsContainerId: Int = R.id.main_fragments_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.lime_600)

        /*val navHostFragment = supportFragmentManager.findFragmentById(fragmentsContainerId) as? NavHostFragment
        navHostFragment?.navController?.let { navController ->
            viewBinding.bottomNavigationMain.setupWithNavController(navController)
        }*/
        supportFragmentManager.beginTransaction()
            .add(
                fragmentsContainerId,
                PermissionRequestFragment.getInstance(),
                PermissionRequestFragment.PERMISSION_REQUEST_FRAGMENT_TAG
            )
            .commit()
    }
}