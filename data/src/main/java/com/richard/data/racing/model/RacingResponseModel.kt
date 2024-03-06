package com.richard.data.racing.model

data class RacingResponseModel(
    val nextToGoIds: List<String>,
    val raceSummaries: RaceSummariesModel,
)
