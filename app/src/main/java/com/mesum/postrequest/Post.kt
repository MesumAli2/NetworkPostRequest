package com.mesum.postrequest

data class Post(
    val  id : Int? = null,
    val userId : Int,
    val name : String,
    val type : String,
    val location : String
    )