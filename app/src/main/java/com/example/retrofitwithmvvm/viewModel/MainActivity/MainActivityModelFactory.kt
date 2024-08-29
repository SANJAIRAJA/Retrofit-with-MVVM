package com.example.retrofitwithmvvm.viewModel.MainActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivityModelFactory(var mainActivityModelFactory: MainActivityRepository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            MainActivityViewModel(this.mainActivityModelFactory) as T
        } else {
            throw IllegalArgumentException("View Not Found")
        }
    }
}