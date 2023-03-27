package com.samples.itis_android_inception_22.di

import com.samples.itis_android_inception_22.presentation.mvvm.MainMvvmFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, PresentationModule::class, DomainModule::class])
interface AppComponent {

    fun inject(fragment: MainMvvmFragment)
}