package com.richard.data.racing.model

import com.google.gson.annotations.SerializedName

data class RacingResponseModel(
    @SerializedName("status")
    val status: Int,
    @SerializedName("data")
    val data: RacingDataModel
)
