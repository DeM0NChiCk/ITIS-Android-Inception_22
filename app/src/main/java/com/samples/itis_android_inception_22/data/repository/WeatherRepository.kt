package com.samples.itis_android_inception_22.data.repository

import com.samples.itis_android_inception_22.data.network.OpenWeatherService
import com.samples.itis_android_inception_22.data.model.response.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository {

    suspend fun getWeatherInfoByCityName(city: String): WeatherResponse? {
        Result
        return withContext(Dispatchers.IO) {
            OpenWeatherService.getInstance()?.getWeatherByCityName(city = city)
        }
    }
}