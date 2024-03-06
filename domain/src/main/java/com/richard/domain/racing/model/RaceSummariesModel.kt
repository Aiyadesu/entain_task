package com.richard.domain.racing.model

data class RaceSummariesModel(
    val raceId: String,
    val raceName: String,
    val raceNumber: String,
    val meetingId: String,
    val meetingName: String,
    val categoryId: String,
    val advertisedStart: Long,
    val raceForm: RaceFormModel,
)
