package com.samples.itis_android_inception_22.presentation.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object JsonService {

    private var retrofitInstance: JsonPlaceholderApi? = null
    private var okHttpClient: OkHttpClient? = null

    private fun createInstance() {
        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofitInstance = retrofit.create(JsonPlaceholderApi::class.java)

    }

    fun getInstance(): JsonPlaceholderApi? {
        return if (retrofitInstance == null) {
            createInstance()
            retrofitInstance
        } else retrofitInstance
    }

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
}