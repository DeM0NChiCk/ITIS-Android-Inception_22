package com.samples.itis_android_inception_22.data.mappers

import com.samples.itis_android_inception_22.data.model.response.WeatherResponse
import com.samples.itis_android_inception_22.domain.entity.WeatherEntity
import javax.inject.Inject

class WeatherResponseMapper @Inject constructor() {

    fun map(item: WeatherResponse?): WeatherEntity {
        return item?.let { response ->
            with(response) {
                WeatherEntity(
                    temperature = main?.temp ?: 0.0f,
                    pressure = main?.pressure ?: 0.0f,
                    humidity = main?.humidity ?: 0.0f
                )
            }
        } ?: WeatherEntity(
            temperature = 0.0f,
            pressure = 0.0f,
            humidity = 0.0f
        )
    }
}



