package com.richard.entain

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(
    navigateToRacingScreen: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(R.string.main_screen_description))
        Button(
            modifier = Modifier
                .padding(4.dp),
            onClick = { navigateToRacingScreen() },
        ) {
            Text(stringResource(R.string.next_to_go_button_label))
        }
    }
}