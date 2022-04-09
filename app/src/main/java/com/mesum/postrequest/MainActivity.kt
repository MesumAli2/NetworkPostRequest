package com.mesum.postrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mesum.postrequest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : MainViewModel
    private  lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        //Initiate ViewModel
        val viewModelFactory = MainViewModel.MainViewModeFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        val post = Post("Mesum","Human", "Earth" )
        viewModel.pushPost(post = post)
        viewModel.myResponse.observe(this, Observer {
            if (it.isSuccessful){
                val reponsecode = it.body()
                var content = ""
                content += "Name ${reponsecode?.name} \n"
                content += "Type ${reponsecode?.type} \n"
                content += "Location ${reponsecode?.location} \n"
                binding.responseText.text = content

                Toast.makeText(this, "${it.body()!!.name.toString()}", Toast.LENGTH_SHORT).show()
                Log.d("Main", it.message().toString())
                Log.d("Main", it.code().toString())
            }

        })

    }


}