package com.example.retrofitwithmvvm.api

import com.example.retrofitwithmvvm.Model.DataResponse
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getData(): DataResponse
}