package com.samples.itis_android_inception_22.data.network

import com.samples.itis_android_inception_22.data.model.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface OpenWeatherApiService {

    @GET("weather")
    suspend fun getWeatherByCityName(
        @Query("q") city: String
    ): WeatherResponse

    @POST
    @Multipart
    suspend fun getWeatherByCityNameQueryMap(
        @QueryMap param: Map<String, String>
    ): WeatherResponse
}