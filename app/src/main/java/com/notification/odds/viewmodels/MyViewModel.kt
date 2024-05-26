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
    var medInc = MutableLiveData<String?>("")
    var houseAge = MutableLiveData<String?>("")
    var avgRooms = MutableLiveData<String?>("")
    var avgBedrms = MutableLiveData<String?>("")
    var population = MutableLiveData<String?>("")
    var avgOccup = MutableLiveData<String?>("")
    var latitide = MutableLiveData<String?>("")
    var longitude = MutableLiveData<String?>("")

    private val _apiResponse = MutableLiveData<MeasureResponse?>()

    val apiResponse: LiveData<MeasureResponse?> = _apiResponse

    fun fetchDataFromApi() {
        var measureRequest = MeasureRequest(convertInt(avgBedrms.value),convertInt(avgOccup.value),convertInt(avgRooms.value),convertInt(houseAge.value),convertInt(latitide.value),convertInt(longitude.value),convertInt(medInc.value),convertInt(population.value))
        viewModelScope.launch {
            try {
                val response = RetrofitBuilder.apiService.getHousePrice(measureRequest)
                if (response.isSuccessful) {
                    _apiResponse.value = response.body()
                } else {
                    isProgress.set(false)
                    throw IOException("Error fetching data")
                }
            } catch (e: Exception) {
                isProgress.set(false)
                e.printStackTrace()
            }
        }
    }

    fun convertInt(value:String?="0") : Int {
        return if (!value.isNullOrEmpty()) value.toInt() else 0
    }

}