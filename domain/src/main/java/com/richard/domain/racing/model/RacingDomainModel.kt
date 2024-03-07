package com.richard.domain.racing.model

data class RacingDomainModel(
    val raceId: String,
    val raceNumber: String,
    val meetingName: String,
    val categoryId: String,
    val advertisedStartTime: Long,
    val initialCountdownTime: Long,
)