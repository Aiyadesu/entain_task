package com.richard.data

import com.richard.data.racing.service.RacingApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RetrofitClient {
    private const val BASE_URL = "https://api.neds.com.au/rest/v1/"

    @Provides
    fun createRaceApiService(): RacingApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(RacingApiService::class.java)
    }
}