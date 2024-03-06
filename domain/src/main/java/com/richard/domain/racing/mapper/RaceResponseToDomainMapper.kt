package com.richard.domain.racing.mapper

import android.util.Log
import com.richard.data.racing.model.RacingResponseModel
import com.richard.domain.racing.model.RacingDomainModel

const val ERROR_RACE_NUMBER = -9999
const val ERROR_ADVERTISED_START_TIME: Long = -9999
const val ERROR_DISTANCE = -9999

fun RacingResponseModel.toDomainModel(): List<RacingDomainModel?> {
    val races: MutableList<RacingDomainModel> = mutableListOf()
    try {
        with(data) {
            nextToGoIds.forEach { raceKey ->
            if (raceSummaries.containsKey(raceKey)) {
                with(raceSummaries[raceKey]) {
                    races.add(
                        RacingDomainModel(
                            raceId = this?.race_id ?: "Race ID",
                            raceName = this?.race_name ?: "Race Name",
                            raceNumber = this?.race_number ?: ERROR_RACE_NUMBER,
                            meetingId = this?.meeting_id ?: "Meeting ID",
                            meetingName = this?.meeting_name ?: "Meeting Name",
                            categoryId = this?.category_id ?: "Category ID",
                            advertisedStartTime = this?.advertised_start?.seconds ?: ERROR_ADVERTISED_START_TIME,
                            distance = this?.race_form?.distance ?: ERROR_DISTANCE,
                            distanceId = this?.race_form?.distanceType?.id ?: "Distance Type ID",
                            distanceName = this?.race_form?.distanceType?.name ?: "Distance Name",
                            distanceShortName = this?.race_form?.distanceType?.short_name ?: "Distance Short Name",
                            trackId = this?.race_form?.trackCondition?.id ?: "Track ID",
                            trackName = this?.race_form?.trackCondition?.name ?: "Track Name",
                            trackShortName = this?.race_form?.trackCondition?.shortName ?: "Track Short Name",
                            weatherId = this?.race_form?.trackCondition?.weatherModel?.id ?: "Weather ID",
                            weatherName = this?.race_form?.trackCondition?.weatherModel?.name ?: "Weather Name",
                            weatherShortName = this?.race_form?.trackCondition?.weatherModel?.shortName ?: "Weather Short Name",
                            raceComment = this?.race_form?.trackCondition?.raceComment ?: "Race Comment",
                            venueId = this?.venue_id ?: "Venue ID",
                            venueName = this?.venue_name ?: "Venue Name",
                            venueState = this?.venue_state ?: "Venue State",
                            venueCountry = this?.venue_state ?: "Venue Country",
                        )
                    )
                }
            }
            }
        }
    } catch (e: Exception) {
        Log.e("Entain Task", e.message ?: "Error retrieving races!")
    }

    return races
}