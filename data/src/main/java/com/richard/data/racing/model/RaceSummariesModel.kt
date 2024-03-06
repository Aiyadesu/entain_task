package com.richard.data.racing.model

data class RaceSummariesModel(
    val race_id: String,
    val race_name: String,
    val race_number: Int,
    val meeting_id: String,
    val meeting_name: String,
    val category_id: String,
    val advertised_start: AdvertisedStart,
    val race_form: RaceFormModel,
    val venue_id: String,
    val venue_name: String,
    val venue_state: String,
    val venue_country: String,
)
