package com.richard.domain.racing.mapper

import com.richard.data.racing.model.RacingResponseModel
import com.richard.domain.racing.model.RaceConstants.COUNTDOWN_TIMER_FINISH_TIME
import com.richard.domain.racing.model.RacingDomainModel
import java.time.Instant

private const val ERROR_ADVERTISED_START_TIME: Long = -9999
private const val DEFAULT_STRING = ""

fun RacingResponseModel.toDomainModel(): MutableList<RacingDomainModel> {
    val races: MutableList<RacingDomainModel> = mutableListOf()
    try {
        with(data) {
            nextToGoIds.forEach { raceKey ->
            if (raceSummaries.containsKey(raceKey)) {
                with(raceSummaries[raceKey]) {
                    val countdownTimeInSeconds = getLocalDateTimeFromTimestamp(this?.advertised_start?.seconds ?: ERROR_ADVERTISED_START_TIME)
                    if (countdownTimeInSeconds > COUNTDOWN_TIMER_FINISH_TIME) {
                     races.add(
                        RacingDomainModel(
                            raceId = this?.race_id.toString(),
                            raceNumber = this?.race_number.toString(),
                            meetingName = this?.meeting_name ?: DEFAULT_STRING,
                            categoryId = this?.category_id ?: DEFAULT_STRING,
                            advertisedStartTime = this?.advertised_start?.seconds ?: ERROR_ADVERTISED_START_TIME,
                            initialCountdownTime = countdownTimeInSeconds,
                        )
                     )
                    }
                }
            }
            }
        }
    } catch (e: Exception) {
        // Fail silently
    }

    races.sortBy { it.advertisedStartTime }

    return races
}

private fun getLocalDateTimeFromTimestamp(advertisedTimestamp: Long): Long {
    val currentTime = Instant.now().epochSecond
    val advertisedTime = advertisedTimestamp
    val countdown = advertisedTime - currentTime

    return countdown
}