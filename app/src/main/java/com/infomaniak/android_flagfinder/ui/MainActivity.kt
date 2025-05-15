package com.infomaniak.android_flagfinder.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.infomaniak.android_flagfinder.ui.screen.MainScreen
import com.infomaniak.android_flagfinder.ui.theme.AndroidFlagFinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidFlagFinderTheme {
                MainScreen()
            }
        }
    }
}
