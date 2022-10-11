package com.samples.itis_android_inception_22.presentation

import android.os.Bundle
import androidx.core.content.ContextCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.samples.itis_android_inception_22.R
import com.samples.itis_android_inception_22.databinding.ActivityMainBinding
import com.samples.itis_android_inception_22.presentation.base.BaseActivity
import com.samples.itis_android_inception_22.presentation.fragments.planetsScreen.PlanetsMainFragment

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val viewBinding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    override val fragmentsContainerId: Int = R.id.main_fragments_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.lime_600)

        supportFragmentManager.beginTransaction()
            .add(
                fragmentsContainerId,
                PlanetsMainFragment.getInstance(),
                PlanetsMainFragment.PLANETS_MAIN_FRAGMENT_TAG
            )
            .commit()
    }
}