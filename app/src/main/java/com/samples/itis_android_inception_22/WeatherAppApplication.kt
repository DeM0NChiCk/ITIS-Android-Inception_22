package com.samples.itis_android_inception_22

import android.app.Application
import com.samples.itis_android_inception_22.di.AppComponent
import com.samples.itis_android_inception_22.di.DaggerAppComponent
import com.samples.itis_android_inception_22.di.DaggerRegistrationComponent
import com.samples.itis_android_inception_22.di.RegistrationComponent

class WeatherAppApplication : Application() {

    lateinit var appComponent: AppComponent
    lateinit var registrationComponent: RegistrationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .build()

        registrationComponent = DaggerRegistrationComponent.builder()
            .context(ctx = this)
            .build()
    }
}