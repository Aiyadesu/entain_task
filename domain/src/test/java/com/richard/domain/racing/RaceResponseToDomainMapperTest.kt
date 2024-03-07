package com.richard.domain.racing

import com.richard.data.racing.model.AdvertisedStart
import com.richard.data.racing.model.RaceSummariesModel
import com.richard.data.racing.model.RacingDataModel
import com.richard.data.racing.model.RacingResponseModel
import com.richard.domain.racing.mapper.toDomainModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.time.Instant

class RaceResponseToDomainMapperTest {
    private val mockRacingResponseModel: RacingResponseModel = mockk(relaxed = true)
    private val mockRacingDataModel: RacingDataModel = mockk(relaxed = true)
    private val mockRaceSummariesModel: RaceSummariesModel = mockk(relaxed = true)
    private val mockSecondRaceSummariesModel: RaceSummariesModel = mockk(relaxed = true)
    private val mockInstant = mockkStatic(Instant::class)

    @Test
    fun `Given racing data When current time is less than 60 seconds past advertised start Then returns list with the racing data`() {
        every { Instant.now().epochSecond } returns 1709816511
        every { mockRaceSummariesModel.race_id } returns "raceId"
        every { mockRaceSummariesModel.race_number } returns 4
        every { mockRaceSummariesModel.meeting_name } returns "meetingName"
        every { mockRaceSummariesModel.category_id } returns "categoryId"
        every { mockRaceSummariesModel.advertised_start } returns AdvertisedStart(1709816511)
        every { mockRacingDataModel.nextToGoIds } returns listOf("1", "2", "3")
        every { mockRacingDataModel.raceSummaries} returns mapOf("1" to mockRaceSummariesModel)
        every { mockRacingResponseModel.data } returns mockRacingDataModel

        val actual = mockRacingResponseModel.toDomainModel()

        with(actual.first()) {
            assertEquals("raceId", this.raceId)
            assertEquals("4", this.raceNumber)
            assertEquals("meetingName", this.meetingName)
            assertEquals("categoryId", this.categoryId)
            assertEquals(1709816511, this.advertisedStartTime)
        }
    }

    @Test
    fun `Given racing data When current time is more than 60 seconds after advertised start Then race does not get added to the list`() {
        every { Instant.now().epochSecond } returns 1709816571
        every { mockRaceSummariesModel.race_id } returns "raceId"
        every { mockRaceSummariesModel.race_number } returns 4
        every { mockRaceSummariesModel.meeting_name } returns "meetingName"
        every { mockRaceSummariesModel.category_id } returns "categoryId"
        every { mockRaceSummariesModel.advertised_start } returns AdvertisedStart(1709816511)
        every { mockRacingDataModel.nextToGoIds } returns listOf("1", "2", "3")
        every { mockRacingDataModel.raceSummaries} returns mapOf("1" to mockRaceSummariesModel)
        every { mockRacingResponseModel.data } returns mockRacingDataModel

        val actual = mockRacingResponseModel.toDomainModel()

        assert(actual.isEmpty())
    }

    @Test
    fun `Given racing data When map of races advertised is not ascending by advertised_start Then races are added in ascending order`() {
        every { Instant.now().epochSecond } returns 1709816511

        // First Race
        every { mockRaceSummariesModel.race_id } returns "raceId"
        every { mockRaceSummariesModel.race_number } returns 4
        every { mockRaceSummariesModel.meeting_name } returns "meetingName"
        every { mockRaceSummariesModel.category_id } returns "categoryId"
        every { mockRaceSummariesModel.advertised_start } returns AdvertisedStart(1709816511)

        // Second Race
        every { mockSecondRaceSummariesModel.race_id } returns "raceId2"
        every { mockSecondRaceSummariesModel.race_number } returns 5
        every { mockSecondRaceSummariesModel.meeting_name } returns "meetingName2"
        every { mockSecondRaceSummariesModel.category_id } returns "categoryId2"
        every { mockSecondRaceSummariesModel.advertised_start } returns AdvertisedStart(1709816512)

        every { mockRacingDataModel.nextToGoIds } returns listOf("1", "2", "3")
        every { mockRacingDataModel.raceSummaries} returns mapOf(
            "1" to mockRaceSummariesModel,
            "2" to mockSecondRaceSummariesModel,
        )
        every { mockRacingResponseModel.data } returns mockRacingDataModel

        val actual = mockRacingResponseModel.toDomainModel()
        assertEquals(2, actual.size)
        val firstRace = actual[0]
        val secondRace = actual[1]

        assertEquals(firstRace.advertisedStartTime, 1709816511)
        assertEquals(secondRace.advertisedStartTime, 1709816512)
    }
}