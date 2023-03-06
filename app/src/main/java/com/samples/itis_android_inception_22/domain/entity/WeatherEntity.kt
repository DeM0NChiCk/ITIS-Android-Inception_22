package com.samples.itis_android_inception_22.domain.entity

import com.samples.itis_android_inception_22.presentation.model.WeatherDataModel

data class WeatherEntity(
    val temperature: Float,
    val pressure: Float,
    val humidity: Float
)

fun WeatherEntity.mapWeatherEntity(): WeatherDataModel {
    return WeatherDataModel(
        temperature = this.temperature,
        pressure = this.pressure,
        humidity = this.humidity
    )
}