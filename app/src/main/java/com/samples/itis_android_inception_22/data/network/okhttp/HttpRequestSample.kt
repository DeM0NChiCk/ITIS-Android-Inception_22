package com.samples.itis_android_inception_22.data.network.okhttp

import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor

private val contentType = "application/json; charset=utf-8".toMediaType()

fun main() {
    val loggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    val gson = Gson()

    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val newPostBody = PostsRequestBody(
        id = 1L,
        title = "SampleTitle",
        body = "SampleBody",
        userId = 8800L
    )

    val newPostBodyString = gson.toJson(newPostBody)
    val okHttpRequestBody = newPostBodyString.toRequestBody(contentType)

    val request = Request.Builder()
        .post(okHttpRequestBody)
        .url("https://jsonplaceholder.typicode.com/posts")
        .build()

    val call = client.newCall(request)
    val response = call.execute()

    if (response.isSuccessful) {
        response.body?.string()?.let { responseBodyString ->
            val signInResponseBody = gson.fromJson(responseBodyString, PostsResponseBody::class.java)
            println("TEST TAG - Received Id: ${signInResponseBody.id}")
        }
    } else {
        throw java.lang.IllegalStateException()
    }
}

data class PostsRequestBody(
    val id: Long,
    val title: String,
    val body: String,
    val userId: Long
)

data class PostsResponseBody(
    val id: Long? = null
)