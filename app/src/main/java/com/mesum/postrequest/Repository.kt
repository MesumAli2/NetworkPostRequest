package com.mesum.postrequest

import retrofit2.Response

class Repository {

    suspend fun pushPost(post : Post) : Response<Post>{
        return PostApi.postRequest.pushPost(post)
    }
}