package com.samples.itis_android_inception_22.di

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.samples.itis_android_inception_22.WeatherAppApplication

fun Context.appComponent(): AppComponent {
    return when (this) {
        is WeatherAppApplication -> appComponent
        else -> this.applicationContext.appComponent()
    }
}

inline fun <reified T : ViewModel> Fragment.lazyViewModel(
    noinline create: (stateHandle: SavedStateHandle) -> T
) = viewModels<T> {
    MainVMFactory(this, create)
}