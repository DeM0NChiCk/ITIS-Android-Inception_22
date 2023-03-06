package com.samples.itis_android_inception_22.domain.repository

import com.samples.itis_android_inception_22.domain.entity.WeatherEntity

interface WeatherRepository {

    suspend fun getWeatherInfoByCityName(city: String): WeatherEntity
}

