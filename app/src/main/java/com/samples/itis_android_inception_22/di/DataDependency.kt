package com.samples.itis_android_inception_22.di

import com.samples.itis_android_inception_22.data.mappers.WeatherResponseMapper
import com.samples.itis_android_inception_22.data.network.OpenWeatherApiService
import com.samples.itis_android_inception_22.data.network.OpenWeatherService
import com.samples.itis_android_inception_22.data.repository.WeatherRepositoryImpl
import com.samples.itis_android_inception_22.domain.repository.WeatherRepository
import com.samples.itis_android_inception_22.domain.usecase.GetWeatherByCityNameUseCase

object DataDependency {

    private val weatherResponseMapper = WeatherResponseMapper()

    private val openWeatherApi: OpenWeatherApiService = OpenWeatherService.getInstance()

    private val weatherRepository: WeatherRepository = WeatherRepositoryImpl(
        remoteSource = openWeatherApi,
        localSource = Any(),
        weatherResponseMapper = weatherResponseMapper
    )

    val getWeatherUseCase: GetWeatherByCityNameUseCase = GetWeatherByCityNameUseCase(weatherRepository)
}