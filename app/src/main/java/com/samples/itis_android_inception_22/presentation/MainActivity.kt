package com.samples.itis_android_inception_22.presentation

import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.samples.itis_android_inception_22.R
import com.samples.itis_android_inception_22.databinding.ActivityMainBinding
import com.samples.itis_android_inception_22.presentation.base.BaseActivity
import com.samples.itis_android_inception_22.presentation.mvvm.MainMvvmFragment
import com.samples.itis_android_inception_22.presentation.screen.MainFragment

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val containerId: Int = R.id.main_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
            .add(containerId, MainMvvmFragment.getInstance(), MainFragment.MAIN_FRAGMENT_TAG)
            .commit()
    }
}