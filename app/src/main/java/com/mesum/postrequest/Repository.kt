package com.mesum.postrequest

import retrofit2.Response
import retrofit2.http.FieldMap

class Repository {

    suspend fun pushPost(post : Post) : Response<Post>{
        return PostApi.postRequest.pushPost(post)
    }

    suspend fun createPost(post: Post): Response<Post>{
        return PostApi.postRequest.createPost(post.userId , post.name, post.type, post.location)
    }

    suspend fun createPostFieldMap(field : Map<String, String>) : Response<Post>{
        return PostApi.postRequest.createPostWithMap(field)
    }

}