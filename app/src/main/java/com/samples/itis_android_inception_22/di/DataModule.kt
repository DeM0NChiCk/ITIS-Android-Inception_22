package com.samples.itis_android_inception_22.di

import com.samples.itis_android_inception_22.BuildConfig
import com.samples.itis_android_inception_22.data.local.LocalSource
import com.samples.itis_android_inception_22.data.mappers.WeatherResponseMapper
import com.samples.itis_android_inception_22.data.network.OpenWeatherApiService
import com.samples.itis_android_inception_22.data.network.OpenWeatherService
import com.samples.itis_android_inception_22.data.repository.WeatherRepositoryImpl
import com.samples.itis_android_inception_22.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    fun okHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val modifiedUrl = chain.request().url.newBuilder()
                    .addQueryParameter("appid", BuildConfig.WEATHER_KEY)
                    .addQueryParameter("units", "metric")
                    .build()

                val request = chain.request().newBuilder().url(modifiedUrl).build()
                chain.proceed(request)
            }
        if (BuildConfig.DEBUG) {
            client.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        return client.build()
    }

    @Provides
    @Singleton
    fun provideOpenWeatherApi(okHttpClient: OkHttpClient): OpenWeatherApiService {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BuildConfig.WEATHER_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofitBuilder.create(OpenWeatherApiService::class.java)
    }
}