package com.mesum.postrequest

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))

        .build()
}

interface PostInterface{

    @POST("posts")
    suspend fun pushPost (
        @Body post: Post
    ) : Response<Post>

}
object PostApi {
    val postRequest : PostInterface by lazy {
        retrofit.create(PostInterface::class.java)
    }
}
