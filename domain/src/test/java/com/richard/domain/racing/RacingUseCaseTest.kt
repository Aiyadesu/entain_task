package com.richard.domain.racing

import com.richard.data.racing.model.AdvertisedStart
import com.richard.data.racing.model.RaceSummariesModel
import com.richard.data.racing.model.RacingDataModel
import com.richard.data.racing.model.RacingResponseModel
import com.richard.data.racing.service.RacingApiService
import com.richard.domain.racing.usecase.RacingUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.time.Instant

class RacingUseCaseTest {
    private val mockRacingResponseModel: RacingResponseModel = mockk(relaxed = true)
    private val mockRacingDataModel: RacingDataModel = mockk(relaxed = true)
    private val mockRacingApiService: RacingApiService = mockk(relaxed = true)

    private val mockFirstRace: RaceSummariesModel = mockk(relaxed = true)
    private val mockSecondRace: RaceSummariesModel = mockk(relaxed = true)
    private val mockThirdRace: RaceSummariesModel = mockk(relaxed = true)
    private val mockFourthRace: RaceSummariesModel = mockk(relaxed = true)
    private val mockInstant = mockkStatic(Instant::class)

    private lateinit var racingUseCase: RacingUseCase

    @Test
    fun `Given more than 5 of each race type When getRaces Then each list in RacesDomainModel has a size of 5`() = runTest {
        every { Instant.now().epochSecond } returns 1709816511

        every { mockFirstRace.category_id } returns "9daef0d7-bf3c-4f50-921d-8e818c60fe61"
        every { mockFirstRace.advertised_start } returns AdvertisedStart(1709816511)
        every { mockSecondRace.category_id } returns "161d9be2-e909-4326-8c2c-35ed71fb460b"
        every { mockSecondRace.advertised_start } returns AdvertisedStart(1709816511)
        every { mockThirdRace.category_id } returns "4a2788f8-e825-4d36-9894-efd4baf1cfae"
        every { mockThirdRace.advertised_start } returns AdvertisedStart(1709816511)
        every { mockFourthRace.category_id } returns "9daef0d7-bf3c-4f50-921d-8e818c60fe61"
        every { mockFourthRace.advertised_start } returns AdvertisedStart(1709816511)

        every { mockRacingDataModel.nextToGoIds } returns listOf(
            "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "10", "11", "12", "13", "14", "15", "16", "17",
        )
        every { mockRacingDataModel.raceSummaries } returns mapOf(
            "1" to mockFirstRace,
            "2" to mockFirstRace,
            "3" to mockFirstRace,
            "4" to mockFirstRace,
            "5" to mockFirstRace,
            "6" to mockSecondRace,
            "7" to mockSecondRace,
            "8" to mockSecondRace,
            "9" to mockSecondRace,
            "10" to mockSecondRace,
            "11" to mockThirdRace,
            "12" to mockThirdRace,
            "13" to mockThirdRace,
            "14" to mockThirdRace,
            "15" to mockThirdRace,
            "16" to mockFirstRace,
            "17" to mockSecondRace,
            "18" to mockThirdRace,
        )
        every { mockRacingResponseModel.data } returns mockRacingDataModel
        coEvery { mockRacingApiService.getRaces(any(), any()) } returns mockRacingResponseModel


        racingUseCase = RacingUseCase(mockRacingApiService)

        val actual = racingUseCase.getRaces()

        assertEquals(5, actual.allRaces.size)
        assertEquals(5, actual.horseRaces.size)
        assertEquals(5, actual.harnessRaces.size)
        assertEquals(5, actual.greyhoundRaces.size)
    }

    @Test
    fun `Given all races When getRaces Then each list contains its corresponding categoryId`() = runTest {
        every { Instant.now().epochSecond } returns 1709816511

        every { mockFirstRace.category_id } returns "9daef0d7-bf3c-4f50-921d-8e818c60fe61"
        every { mockFirstRace.advertised_start } returns AdvertisedStart(1709816511)
        every { mockSecondRace.category_id } returns "161d9be2-e909-4326-8c2c-35ed71fb460b"
        every { mockSecondRace.advertised_start } returns AdvertisedStart(1709816511)
        every { mockThirdRace.category_id } returns "4a2788f8-e825-4d36-9894-efd4baf1cfae"
        every { mockThirdRace.advertised_start } returns AdvertisedStart(1709816511)
        every { mockFourthRace.category_id } returns "9daef0d7-bf3c-4f50-921d-8e818c60fe61"
        every { mockFourthRace.advertised_start } returns AdvertisedStart(1709816511)

        every { mockRacingDataModel.nextToGoIds } returns listOf(
            "1", "2", "3", "4", "5", "6", "7", "8",
        )
        every { mockRacingDataModel.raceSummaries } returns mapOf(
            "1" to mockFirstRace,
            "2" to mockSecondRace,
            "3" to mockThirdRace,
            "4" to mockFourthRace,
            "5" to mockFirstRace,
            "6" to mockSecondRace,
            "7" to mockThirdRace,
            "8" to mockFourthRace,
        )
        every { mockRacingResponseModel.data } returns mockRacingDataModel
        coEvery { mockRacingApiService.getRaces(any(), any()) } returns mockRacingResponseModel


        racingUseCase = RacingUseCase(mockRacingApiService)

        val actual = racingUseCase.getRaces()

        actual.horseRaces.forEach {
            assertEquals("4a2788f8-e825-4d36-9894-efd4baf1cfae", it.categoryId)
        }

        actual.harnessRaces.forEach {
            assertEquals("161d9be2-e909-4326-8c2c-35ed71fb460b", it.categoryId)
        }

        actual.greyhoundRaces.forEach {
            assertEquals("9daef0d7-bf3c-4f50-921d-8e818c60fe61", it.categoryId)
        }
    }
}