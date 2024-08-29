package com.example.retrofitwithmvvm.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.retrofitwithmvvm.api.RetrofitInstance
import com.example.retrofitwithmvvm.databinding.ActivityMainBinding
import com.example.retrofitwithmvvm.viewModel.MainActivity.MainActivityModelFactory
import com.example.retrofitwithmvvm.viewModel.MainActivity.MainActivityRepository
import com.example.retrofitwithmvvm.viewModel.MainActivity.MainActivityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val viewModel: MainActivityViewModel by viewModels {
        MainActivityModelFactory(MainActivityRepository())
    }

    companion object{
        val TAG = "MainActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpObserver()
        init()
    }

    private fun setUpObserver() {
        viewModel.successResponse.observe(this){ success->
            success.let {
                it.forEach{i->
                    Log.d(TAG,"${i.id}")
                }
            }
        }

        viewModel.errorMessage.observe(this){error->
            error.let {
                Log.d(TAG,"${it}")
            }
        }
    }

    private fun init(){
        binding.tvMakeApiCall.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.getData()
            }
        }
    }
}