package com.richard.data.racing.model

data class TrackConditionModel(
    val id: String,
    val name: String,
    val shortName: String,
    val weatherModel: WeatherModel? = null,
    val raceComment: String,
)
