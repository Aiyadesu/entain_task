package com.richard.domain.racing.model

class RaceFormModel(
    val distance: Long,
    val distanceType: DistanceTypeModel,
    val trackCondition: TrackConditionModel,
    val venueId: VenueModel,
)