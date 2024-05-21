package com.notification.odds.retrofitrelated

import MeasureRequest
import MeasureResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


object RetrofitBuilder {
    private const val BASE_URL = "https://gayatridgd.pythonanywhere.com/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: MyApiInterface = getRetrofit().create(MyApiInterface::class.java)
}

interface MyApiInterface {

    @POST("predict")
    suspend fun getHousePrice(@Body reqBody: MeasureRequest): Response<MeasureResponse?>




}
