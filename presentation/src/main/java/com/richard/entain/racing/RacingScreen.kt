package com.richard.entain.racing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.richard.domain.racing.model.RacingDomainModel
import com.richard.entain.R
import com.richard.entain.racing.model.SelectedRace
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RacingScreen(
    racingViewModel: RacingViewModel,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {

        var greyhoundFilterSelected by remember { mutableStateOf(false) }
        var harnessFilterSelected by remember { mutableStateOf(false) }
        var horseFilterSelected by remember { mutableStateOf(false) }

        var selectedRace by remember { mutableStateOf(SelectedRace.NONE) }
        val races = remember { racingViewModel.races }

        Row(
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            FilterChip(
                modifier = Modifier
                    .semantics {
                        contentDescription = "Horse Races Filter Button"
                    },
                label = { Text(stringResource(R.string.filter_horse_label)) },
                onClick = {
                    horseFilterSelected = !horseFilterSelected
                    greyhoundFilterSelected = false
                    harnessFilterSelected = false

                    selectedRace = setSelectedRaceFilter(isHorseSelected = horseFilterSelected)

                    racingViewModel.getFilteredRaces(selectedRace)
                },
                selected = horseFilterSelected,
            )
            FilterChip(
                modifier = Modifier
                    .semantics {
                        contentDescription = "Harness Races Filter Button"
                    },
                label = { Text(stringResource(R.string.filter_harness_label)) },
                onClick = {
                    harnessFilterSelected = !harnessFilterSelected
                    greyhoundFilterSelected = false
                    horseFilterSelected = false

                    selectedRace = setSelectedRaceFilter(isHarnessSelected = harnessFilterSelected)

                    racingViewModel.getFilteredRaces(selectedRace)
                },
                selected = harnessFilterSelected,
            )
            FilterChip(
                modifier = Modifier
                    .semantics {
                        contentDescription = "Greyhound Races Filter Button"
                    },
                label = { Text(stringResource(R.string.filter_greyhound_label)) },
                onClick = {
                    greyhoundFilterSelected = !greyhoundFilterSelected
                    harnessFilterSelected = false
                    horseFilterSelected = false

                    selectedRace = setSelectedRaceFilter(isGreyhoundSelected = greyhoundFilterSelected)

                    racingViewModel.getFilteredRaces(selectedRace)
                },
                selected = greyhoundFilterSelected,
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            items(races) { race ->
                RaceInformation(race) { racingViewModel.removeRace(selectedRace) }
            }
        }
    }
}

@Composable
private fun RaceInformation(
    race: RacingDomainModel,
    onCountdownFinished: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(
                modifier = Modifier
                    .semantics {
                        contentDescription = "Meeting Name: ${race.meetingName}"
                    },
                text = "${stringResource(R.string.meeting_name_label)} ${race.meetingName}"
            )
            Text(
                modifier = Modifier
                    .semantics {
                        contentDescription = "Race Number: ${race.raceNumber}"
                    },
                text = "${stringResource(R.string.race_number_label)} ${race.raceNumber}"
            )
        }

        Column {
            CountdownTimer(
                onCountdownFinished = {
                    onCountdownFinished()
                },
                initialValue = race.initialCountdownTime,
            )
        }
    }
    Divider()
}

@Composable
private fun CountdownTimer(
    onCountdownFinished: () -> Unit,
    initialValue: Long
) {
    var countdownValue by remember { mutableLongStateOf(initialValue) }

    val countdownThresholdSeconds = -60
    val countdownIntervalMilliseconds = 1000L
    val countdownIntervalSeconds = 1

    LaunchedEffect(countdownValue) {
        while (countdownValue > countdownThresholdSeconds) {
            delay(countdownIntervalMilliseconds)
            countdownValue -= countdownIntervalSeconds
        }

        onCountdownFinished()
    }

    Text(
        modifier = Modifier
            .semantics {
                contentDescription = "Advertised Start Time as Countdown: $countdownValue"
            },
        text = stringResource(R.string.seconds, countdownValue),
        color = Color.Red,
    )
}

private fun setSelectedRaceFilter(
    isNone: Boolean = false,
    isHorseSelected: Boolean = false,
    isHarnessSelected: Boolean = false,
    isGreyhoundSelected: Boolean = false,
): SelectedRace {
    return when {
        isNone -> SelectedRace.NONE
        isHorseSelected -> SelectedRace.HORSE
        isHarnessSelected -> SelectedRace.HARNESS
        isGreyhoundSelected -> SelectedRace.GREYHOUND
        else -> SelectedRace.NONE
    }
}