package com.richard.entain.racing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment

@Composable
fun RacingScreen(
    racingViewModel: RacingViewModel,
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        val racingData = racingViewModel.dataResult.observeAsState()
        Text(text = "Hello World")
    }
}