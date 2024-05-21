package com.notification.odds.viewmodels

import MeasureRequest
import MeasureResponse
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notification.odds.retrofitrelated.RetrofitBuilder
import kotlinx.coroutines.launch
import java.io.IOException

class MyViewModel : ViewModel() {
    var remainingRe = ObservableField("")
    var isProgress = ObservableField(false)
    var result = ObservableField(0.0)
    private val _apiResponse = MutableLiveData<MeasureResponse?>()

    val apiResponse: LiveData<MeasureResponse?> = _apiResponse

    fun fetchDataFromApi() {
        var measureRequest = MeasureRequest(3,22,4,1,1200,2,37,-122)
        viewModelScope.launch {
            try {
                val response = RetrofitBuilder.apiService.getHousePrice(measureRequest)
                if (response.isSuccessful) {
                    _apiResponse.value = response.body()
                } else {
                    throw IOException("Error fetching data")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}