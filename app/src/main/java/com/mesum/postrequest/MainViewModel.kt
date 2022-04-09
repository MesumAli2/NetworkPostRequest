package com.mesum.postrequest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val myResponse : MutableLiveData<Response<Post>> = MutableLiveData()

    fun pushPost(post: Post){

        viewModelScope.launch{
            val response = repository.pushPost(post)
            if (response.isSuccessful){
                myResponse.value = response

            }
        }
    }

    fun createPost(post: Post){
        viewModelScope.launch {
            val response = repository.createPost(post)
            if (response.isSuccessful){
                myResponse.value = response
            }
        }
    }

    class MainViewModeFactory(val repository: Repository) : ViewModelProvider.Factory{


        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)){
                return MainViewModel(repository) as T
            }

            throw  IllegalAccessException("ViewModel Not Created")        }

    }
}