package com.samples.itis_android_inception_22.data.repository

import com.samples.itis_android_inception_22.data.mappers.WeatherResponseMapper
import com.samples.itis_android_inception_22.data.network.OpenWeatherApiService
import com.samples.itis_android_inception_22.domain.entity.WeatherEntity
import com.samples.itis_android_inception_22.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepositoryImpl(
    private val remoteSource: OpenWeatherApiService,
    private val localSource: Any,
    private val weatherResponseMapper: WeatherResponseMapper
): WeatherRepository  {

    override suspend fun getWeatherInfoByCityName(city: String): WeatherEntity {
        return withContext(Dispatchers.IO) {
            (weatherResponseMapper::map)(remoteSource.getWeatherByCityName(city = city))
        }
    }
}