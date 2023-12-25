package com.dreamsoftware.voxlet.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = DestinationType.LoginNavigation.route
    ) {
        loginGraph(
            onNavigateToHome = {
                navigateToDestination(navController, DestinationType.HomeNavigation.route)
            },
            onNavigateToEditProfile = {
                navigateToDestination(navController, DestinationType.ProfileEdit.route)
            }
        )
        homeGraph(
            onBack = {
                navController.popBackStack()
            }
        )
    }
}

private fun navigateToDestination(navController: NavHostController, route: String) {
    navController.navigate(route) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}