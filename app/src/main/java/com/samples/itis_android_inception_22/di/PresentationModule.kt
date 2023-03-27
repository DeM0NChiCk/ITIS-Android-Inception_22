package com.samples.itis_android_inception_22.di

import androidx.lifecycle.viewmodel.CreationExtras
import com.samples.itis_android_inception_22.domain.usecase.GetWeatherByCityNameUseCase
import com.samples.itis_android_inception_22.presentation.mvvm.MainFragmentViewModel
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun provideMainViewModel(useCase: GetWeatherByCityNameUseCase): MainFragmentViewModel {
        return MainFragmentViewModel(useCase)
    }
}