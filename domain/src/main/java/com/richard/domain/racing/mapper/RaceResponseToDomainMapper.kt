package com.richard.domain.racing.mapper

import com.richard.data.racing.model.RacingResponseModel
import com.richard.domain.racing.model.RacingDomainModel

fun RacingResponseModel.toDomainModel(): RacingDomainModel? {
    return try {
        with(this) {
            RacingDomainModel(
                raceId = raceSummaries.raceId,
                raceName = raceSummaries.raceName,
                raceNumber = raceSummaries.raceNumber,
                meetingId = raceSummaries.meetingId,
                meetingName = raceSummaries.meetingName,
                categoryId = raceSummaries.categoryId,
                advertisedStartTime = raceSummaries.advertisedStart,
                distance = raceSummaries.raceForm.distance,
                distanceId = raceSummaries.raceForm.distanceType.id,
                distanceName = raceSummaries.raceForm.distanceType.name,
                distanceShortName = raceSummaries.raceForm.distanceType.shortName,
                trackId = raceSummaries.raceForm.trackCondition.id,
                trackName = raceSummaries.raceForm.trackCondition.name,
                trackShortName = raceSummaries.raceForm.trackCondition.shortName,
                weatherId = raceSummaries.raceForm.trackCondition.weatherModel?.id ?: "",
                weatherName = raceSummaries.raceForm.trackCondition.weatherModel?.name ?: "",
                weatherShortName = raceSummaries.raceForm.trackCondition.weatherModel?.shortName ?: "",
                raceComment = raceSummaries.raceForm.trackCondition.raceComment,
                venueId = raceSummaries.raceForm.venueId.venueId,
                venueName = raceSummaries.raceForm.venueId.venueName,
                venueState = raceSummaries.raceForm.venueId.venueState,
                venueCountry = raceSummaries.raceForm.venueId.venueCountry,
            )
        }
    } catch (e: Exception) {
        null
    }
}