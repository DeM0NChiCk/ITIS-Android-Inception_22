package com.samples.itis_android_inception_22.data.network

import com.samples.itis_android_inception_22.data.model.response.PostsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface JsonPlaceholderApi {

    @GET("posts")
    suspend fun getPostsList(): List<PostsResponse>

    @GET("posts/{postId}")
    suspend fun getParticularPostInfo(@Path("postId") id: String): PostsResponse

    @GET("posts/{postId}")
    suspend fun getParticularPostInfoWithQuery(
        @Path("postId") id: String,
        @Query("name") nameQuery: String? = null,
        @Query("filter") filterQuery: String? = null
    ): PostsResponse

   /* @POST("posts")
    suspend fun createNewPost(
        @Header("Authorization") header: String,
        @Body requestBody: PostsRequestBodySample
    ): PostsResponse*/
}