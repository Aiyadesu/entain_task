package com.richard.data

import com.richard.data.racing.service.RacingApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.neds.com.au/rest/v1/"

    fun createRaceApiService(): RacingApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(RacingApiService::class.java)
    }
}