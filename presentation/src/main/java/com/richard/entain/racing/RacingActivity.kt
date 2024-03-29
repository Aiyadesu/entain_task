package com.richard.entain.racing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.richard.entain.ui.theme.EntainTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RacingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EntainTheme {
                val racingViewModel: RacingViewModel by viewModels()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RacingScreen(racingViewModel)
                }
            }
        }
    }
}