package com.samples.itis_android_inception_22.presentation.okhttp

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

private val content = "application/json; charset=utf-8".toMediaType()

fun main() {
    val loggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    val gson = Gson()

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val requestBody = PostsRequestBodySample(
        title = "firstTitle",
        body = "bodySample",
        userId = 101L
    )

    val requestBodyString = gson.toJson(requestBody)


}

data class PostsRequestBodySample(
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("userId")
    val userId: Long
)

data class PostsResponseS2(
    @SerializedName("userId")
    val userId: Long? = null,
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("body")
    val body: String? = null
)