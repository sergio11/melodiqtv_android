package com.dreamsoftware.melodiqtv.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.dreamsoftware.melodiqtv.ui.receiver.ScreenStateReceiver
import com.dreamsoftware.melodiqtv.ui.screens.app.AppScreen
import com.dreamsoftware.melodiqtv.utils.network.NetworkConnectivityMonitor
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var screenStateReceiver: ScreenStateReceiver

    @Inject
    lateinit var networkConnectivityMonitor: NetworkConnectivityMonitor

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        screenStateReceiver = ScreenStateReceiver.register(this)
        networkConnectivityMonitor.registerNetworkCallback()
        setContent {
            AppScreen()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ScreenStateReceiver.unregister(this, screenStateReceiver)
        networkConnectivityMonitor.unregisterNetworkCallback()
    }
}