package com.samples.itis_android_inception_22

import android.app.Application
import com.samples.itis_android_inception_22.di.AppComponent
import com.samples.itis_android_inception_22.di.DaggerAppComponent

class WeatherAppApplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .build()
    }
}