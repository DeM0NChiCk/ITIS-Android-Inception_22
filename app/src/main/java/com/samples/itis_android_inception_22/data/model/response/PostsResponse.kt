package com.samples.itis_android_inception_22.data.model.response

import com.google.gson.annotations.SerializedName

data class PostsResponse(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("body")
    val body: String? = null,
    @SerializedName("userId")
    val userId: Long? = null
)
