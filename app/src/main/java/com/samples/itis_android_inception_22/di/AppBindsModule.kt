package com.samples.itis_android_inception_22.di

import com.samples.itis_android_inception_22.data.repository.WeatherRepositoryImpl
import com.samples.itis_android_inception_22.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module

@Module
abstract class AppBindsModule {

    @Binds
    abstract fun bindWeatherRepositoryImpl_to_WeatherRepository(repositoryImpl: WeatherRepositoryImpl): WeatherRepository
}