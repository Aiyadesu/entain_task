package com.richard.domain.racing.usecase

import com.richard.data.racing.service.RacingApiService
import com.richard.domain.racing.mapper.toDomainModel
import com.richard.domain.racing.model.RacingDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RacingUseCase @Inject constructor(
    val racingApiService: RacingApiService,
) {
    suspend fun getRaces(): RacingDomainModel? {
        val method = "nextraces"
        val count = "10"
        return withContext(Dispatchers.IO) {
            return@withContext racingApiService.getRaces(method, count).toDomainModel()
        }
    }
}