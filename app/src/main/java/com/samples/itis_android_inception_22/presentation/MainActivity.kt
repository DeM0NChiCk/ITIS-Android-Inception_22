package com.samples.itis_android_inception_22.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.samples.itis_android_inception_22.R
import com.samples.itis_android_inception_22.databinding.ActivityMainBinding
import com.samples.itis_android_inception_22.domain.usecase.GetWeatherByCityNameUseCase
import com.samples.itis_android_inception_22.presentation.base.BaseActivity
import com.samples.itis_android_inception_22.presentation.mvvm.MainMvvmFragment
import com.samples.itis_android_inception_22.presentation.screen.MainFragment
import javax.inject.Inject

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val containerId: Int = R.id.main_container

    @Inject
    lateinit var weatherUseCase: GetWeatherByCityNameUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
            .add(containerId, MainMvvmFragment.getInstance(), MainMvvmFragment.MAIN_FRAGMENT_TAG)
            .commit()
    }

    fun navigate(destination: Fragment, tag: String? = null, isNeedToDetach: Boolean = false) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(containerId, destination, tag)

        if (isNeedToDetach) {
            val currentFragment =
                supportFragmentManager.findFragmentById(containerId)
                    ?: throw java.lang.IllegalStateException("MainFragmentsContainer is empty")
            transaction.detach(currentFragment)
        }
        transaction.commit()
    }
}