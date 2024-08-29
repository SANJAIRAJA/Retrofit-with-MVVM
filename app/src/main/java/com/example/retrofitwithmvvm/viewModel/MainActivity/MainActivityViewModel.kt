package com.example.retrofitwithmvvm.viewModel.MainActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitwithmvvm.Model.CommonErrorResult.ResultError
import com.example.retrofitwithmvvm.Model.DataResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.util.concurrent.TimeoutException

class MainActivityViewModel(val repository: MainActivityRepository): ViewModel() {


    var job: Job? = null
    val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    private val _successResponse = MutableLiveData<DataResponse>()
    var successResponse: LiveData<DataResponse> = _successResponse

    private val _errorMessage = MutableLiveData<ResultError>()
    var errorMessage: LiveData<ResultError> = _errorMessage


    fun getData(){
        job = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler)  {
            try {
                val response = repository.getData()
                _successResponse.postValue(response)
            }
            catch (e : TimeoutException){
                _errorMessage.postValue(
                    ResultError(
                        -2,
                        e.message.toString()
                    )
                )
            }
            catch (e: HttpException) {
                _errorMessage.postValue(ResultError(e.code(), e.message()))
            }
            catch (e: Exception) {
                _errorMessage.postValue(ResultError(-1, e.message.toString()))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}