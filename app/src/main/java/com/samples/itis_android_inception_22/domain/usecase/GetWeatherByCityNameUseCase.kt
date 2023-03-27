package com.samples.itis_android_inception_22.domain.usecase

import com.samples.itis_android_inception_22.domain.entity.mapWeatherEntity
import com.samples.itis_android_inception_22.domain.repository.WeatherRepository
import com.samples.itis_android_inception_22.presentation.model.WeatherDataModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetWeatherByCityNameUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    /*suspend fun getWeatherByCityName(city: String): WeatherDataModel {
        return weatherRepository.getWeatherInfoByCityName(city = city).mapWeatherEntity()
    }*/

    suspend operator fun invoke(city: String): WeatherDataModel {
        return weatherRepository.getWeatherInfoByCityName(city).mapWeatherEntity()
    }
}