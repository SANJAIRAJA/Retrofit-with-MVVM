package com.example.retrofitwithmvvm.viewModel.MainActivity

import com.example.retrofitwithmvvm.api.RetrofitInstance

class MainActivityRepository {
    private var api = RetrofitInstance.api

    suspend fun getData() = api.getData()
}