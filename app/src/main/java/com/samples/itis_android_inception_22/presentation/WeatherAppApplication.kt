package com.samples.itis_android_inception_22.presentation

import android.app.Application
import com.samples.itis_android_inception_22.presentation.base.BasePresenter

class WeatherAppApplication: Application() {

    private val presentersMap: HashMap<String, BasePresenter> = hashMapOf()

    override fun onCreate() {
        super.onCreate()
    }
}