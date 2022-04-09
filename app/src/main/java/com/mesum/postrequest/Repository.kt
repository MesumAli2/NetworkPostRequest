package com.mesum.postrequest

import retrofit2.Response

class Repository {

    suspend fun pushPost(post : Post) : Response<Post>{
        return PostApi.postRequest.pushPost(post)
    }

    suspend fun createPost(post: Post): Response<Post>{
        return PostApi.postRequest.createPost(post.userId , post.name, post.type, post.location)
    }

}