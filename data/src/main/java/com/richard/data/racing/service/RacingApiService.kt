package com.richard.data.racing.service

import com.richard.data.racing.model.RacingResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface RacingApiService {
    @GET("/racing")
    suspend fun getRaces(
        @Query("method") method: String,
        @Query("count") count: String,
    ): RacingResponseModel
}