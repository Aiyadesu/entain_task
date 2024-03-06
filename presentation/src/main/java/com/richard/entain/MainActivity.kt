package com.richard.entain

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.richard.entain.racing.RacingActivity
import com.richard.entain.ui.theme.EntainTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        fun navigateToRacingScreen() {
            intent = Intent(this, RacingActivity::class.java)
            startActivity(intent)
        }

        super.onCreate(savedInstanceState)
        setContent {
            EntainTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen { navigateToRacingScreen() }
                }
            }
        }
    }
}