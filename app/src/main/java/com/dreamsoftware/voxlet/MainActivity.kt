package com.dreamsoftware.voxlet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.dreamsoftware.voxlet.ui.VoxLetApp
import com.dreamsoftware.voxlet.ui.theme.VoxLetTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            VoxLetTheme {
                VoxLetApp()
            }
        }
    }
}