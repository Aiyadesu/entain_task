package com.richard.data.racing.model

import com.richard.data.racing.model.RaceSummariesModel

data class RacingResponseModel(
    val nextToGoIds: List<String>,
    val raceSummaries: RaceSummariesModel,
)
