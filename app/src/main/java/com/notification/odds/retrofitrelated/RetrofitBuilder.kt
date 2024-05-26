package com.notification.odds.retrofitrelated

import MeasureRequest
import MeasureResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


object RetrofitBuilder {
    private const val BASE_URL = "https://gayatridgd.pythonanywhere.com/"

    private fun getRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY // Customize as per your needs

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(loggingInterceptor)
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    val apiService: MyApiInterface = getRetrofit().create(MyApiInterface::class.java)
}

interface MyApiInterface {

    @POST("predict")
    suspend fun getHousePrice(@Body reqBody: MeasureRequest): Response<MeasureResponse?>




}
