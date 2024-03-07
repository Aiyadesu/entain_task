package com.richard.entain.racing

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.richard.domain.racing.model.RaceConstants.COUNTDOWN_TIMER_FINISH_TIME
import com.richard.domain.racing.model.RaceConstants.MAXIMUM_NUMBER_OF_RACES_DISPLAYED
import com.richard.domain.racing.model.RaceConstants.POLL_RATE_IN_MILLISECONDS
import com.richard.domain.racing.model.RaceConstants.START_INDEX
import com.richard.domain.racing.model.RacesDomainModel
import com.richard.domain.racing.model.RacingDomainModel
import com.richard.domain.racing.usecase.RacingUseCase
import com.richard.entain.racing.model.SelectedRace
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val DEFAULT_NUMBER = -9999L
private const val DEFAULT_STRING = ""

@HiltViewModel
class RacingViewModel @Inject constructor(
    private val racingUseCase: RacingUseCase
) : ViewModel() {
    private val _racingData = mutableStateListOf(RacingDomainModel(DEFAULT_STRING,DEFAULT_STRING, DEFAULT_STRING, DEFAULT_STRING, DEFAULT_NUMBER, DEFAULT_NUMBER))
    val races get() = _racingData

    init {
        observeAndPollRacingData()
    }

   private fun observeAndPollRacingData() {
        viewModelScope.launch {
            while (true) {
                getRacingData(SelectedRace.NONE)
                delay(POLL_RATE_IN_MILLISECONDS)
            }
        }
    }

   private fun getRacingData(selectedRace: SelectedRace) {
       viewModelScope.launch {
           val races = racingUseCase.getRaces()
           val selectedRaceData = races.getFilteredRace(selectedRace)

           addRaces(selectedRaceData)
       }
   }

    fun removeRace(selectedRace: SelectedRace) {
        val newRaceList = _racingData.toMutableList()
        newRaceList.filter { it.initialCountdownTime > COUNTDOWN_TIMER_FINISH_TIME }
        _racingData.removeAll(newRaceList)
        getRacingData(selectedRace)
    }

    fun getFilteredRaces(selectedRace: SelectedRace) {
        _racingData.clear()
        getRacingData(selectedRace)
    }

    private fun RacesDomainModel.getFilteredRace(selectedRace: SelectedRace): List<RacingDomainModel> {
        return when (selectedRace) {
            SelectedRace.NONE -> this.allRaces
            SelectedRace.HORSE -> this.horseRaces
            SelectedRace.HARNESS -> this.harnessRaces
            SelectedRace.GREYHOUND -> this.greyhoundRaces
        }
    }

    private fun addRaces(selectedRaceData: List<RacingDomainModel>) {
        val amountOfNewRaces = MAXIMUM_NUMBER_OF_RACES_DISPLAYED - _racingData.size
        _racingData.addAll(selectedRaceData.subList(START_INDEX, amountOfNewRaces))
    }
}