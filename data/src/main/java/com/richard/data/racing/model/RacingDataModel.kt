package com.richard.data.racing.model

import com.google.gson.annotations.SerializedName

data class RacingDataModel(
    @SerializedName("next_to_go_ids")
    val nextToGoIds: List<String>,
    @SerializedName("race_summaries")
    val raceSummaries: Map<String, RaceSummariesModel>,
)
