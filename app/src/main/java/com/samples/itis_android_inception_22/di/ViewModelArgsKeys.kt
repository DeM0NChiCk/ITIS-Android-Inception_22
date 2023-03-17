package com.samples.itis_android_inception_22.di

import androidx.lifecycle.viewmodel.CreationExtras
import com.samples.itis_android_inception_22.domain.usecase.GetWeatherByCityNameUseCase

object ViewModelArgsKeys {

    val getWeatherUseCaseKey = object : CreationExtras.Key<GetWeatherByCityNameUseCase> {}

    // Можно использовать обычный класс
    // class GetWeatherUseCaseKey: CreationExtras.Key<GetWeatherByCityNameUseCase>
}