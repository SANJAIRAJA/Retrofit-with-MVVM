package com.example.retrofitwithmvvm.Model

class DataResponse : ArrayList<DataResponseItem>()

data class DataResponseItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)