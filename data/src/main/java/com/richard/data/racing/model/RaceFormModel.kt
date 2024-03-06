package com.richard.data.racing.model

import com.google.gson.annotations.SerializedName

class RaceFormModel(
    val distance: Int,
    @SerializedName("distance_type")
    val distanceType: DistanceType,
    @SerializedName("track_condition")
    val trackCondition: TrackConditionModel,
    val race_comment: String,
    val additional_data: String,
    val generated: Int,
    val silk_base_url: String,
    val race_comment_alternative: String,
)