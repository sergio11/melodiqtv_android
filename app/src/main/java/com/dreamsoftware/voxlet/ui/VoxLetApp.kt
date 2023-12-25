package com.dreamsoftware.voxlet.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dreamsoftware.voxlet.ui.navigation.AppNavHost
import com.dreamsoftware.voxlet.ui.navigation.DestinationType

@Composable
fun VoxLetApp() {
    var showNavigationBar by remember { mutableStateOf(false) }
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    val destinationsWithoutNavigationBar = listOf(
        DestinationType.Login.route,
        DestinationType.ProfileEdit.route,
        DestinationType.Post.route
    )

    LaunchedEffect(currentDestination) {
        showNavigationBar = !destinationsWithoutNavigationBar.contains(currentDestination)
    }

    Box(
        Modifier.fillMaxSize()
    ) {
        Scaffold(
            bottomBar = {
                if (showNavigationBar) {
                    NavigationBar(
                        containerColor = Color.White
                    ) {

                    }
                }
            }
        ) { paddingValues ->
            Column(
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(Modifier.fillMaxSize()) {
                    AppNavHost(navController = navController)
                }
            }
        }
    }
}