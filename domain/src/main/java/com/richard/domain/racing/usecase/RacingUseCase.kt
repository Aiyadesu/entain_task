package com.richard.domain.racing.usecase

import com.richard.data.racing.service.RacingApiService
import com.richard.domain.racing.mapper.toDomainModel
import com.richard.domain.racing.model.RaceConstants.GREYHOUND_RACING_CATEGORY_ID
import com.richard.domain.racing.model.RaceConstants.HARNESS_RACING_CATEGORY_ID
import com.richard.domain.racing.model.RaceConstants.HORSE_RACING_CATEGORY_ID
import com.richard.domain.racing.model.RaceConstants.MAXIMUM_NUMBER_OF_RACES_DISPLAYED
import com.richard.domain.racing.model.RaceConstants.RACES_COUNT_QUERY_PARAMETER
import com.richard.domain.racing.model.RaceConstants.RACES_METHOD_QUERY_PARAMETER
import com.richard.domain.racing.model.RaceConstants.START_INDEX
import com.richard.domain.racing.model.RacesDomainModel
import com.richard.domain.racing.model.RacingDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RacingUseCase @Inject constructor(
    private val racingApiService: RacingApiService,
) {
    suspend fun getRaces(): RacesDomainModel {
        return withContext(Dispatchers.IO) {
            val allRaces = racingApiService
                .getRaces(
                    RACES_METHOD_QUERY_PARAMETER,
                    RACES_COUNT_QUERY_PARAMETER,
                ).toDomainModel()

            return@withContext RacesDomainModel(
                allRaces = allRaces.setMaximumSize(MAXIMUM_NUMBER_OF_RACES_DISPLAYED),
                horseRaces = allRaces.filter { it.categoryId == HORSE_RACING_CATEGORY_ID }.setMaximumSize(MAXIMUM_NUMBER_OF_RACES_DISPLAYED),
                harnessRaces = allRaces.filter { it.categoryId == HARNESS_RACING_CATEGORY_ID }.setMaximumSize(MAXIMUM_NUMBER_OF_RACES_DISPLAYED),
                greyhoundRaces = allRaces.filter { it.categoryId == GREYHOUND_RACING_CATEGORY_ID }.setMaximumSize(MAXIMUM_NUMBER_OF_RACES_DISPLAYED),
            )
        }
    }

    private fun List<RacingDomainModel>.setMaximumSize(maximumRacesShownIndex: Int): MutableList<RacingDomainModel> {
        return if (this.size >= maximumRacesShownIndex) {
            this.subList(START_INDEX, maximumRacesShownIndex) as MutableList<RacingDomainModel>
        } else {
            this as MutableList<RacingDomainModel>
        }
    }
}