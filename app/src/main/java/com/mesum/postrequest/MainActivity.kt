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
    private lateinit var fieldMap : HashMap<String, String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fieldMap = HashMap()
        fieldMap.put("userId", "25")
        fieldMap.put("name", "Henna")
        fieldMap.put("type", "bird")
        fieldMap.put("location", "Mars")


        val repository = Repository()
        //Initiate ViewModel
        val viewModelFactory = MainViewModel.MainViewModeFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        val post = Post(0,23,"Jake","Human", "Earth" )
        viewModel.createPostFieldMap(fieldMap)
        viewModel.myResponse.observe(this, Observer {
            if (it.isSuccessful){
                val reponsecode = it.body()
                var content = ""
                content += "ID ${reponsecode?.id} \n"
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