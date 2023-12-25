package com.dreamsoftware.voxlet.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

internal fun NavGraphBuilder.loginGraph(
    onNavigateToHome: () -> Unit = {},
    onNavigateToEditProfile: () -> Unit = {}
) {
    navigation(
        startDestination = DestinationType.Login.route,
        route = DestinationType.LoginNavigation.route
    ) {
        composable(DestinationType.Login.route) {}
        composable(DestinationType.ProfileEdit.route) {}
    }
}

internal fun NavGraphBuilder.homeGraph(
    onBack: () -> Unit = {}
) {
    navigation(
        startDestination = DestinationType.Feed.route,
        route = DestinationType.HomeNavigation.route
    ) {
        composable(DestinationType.Feed.route) {}
        composable(DestinationType.Search.route) {}
        composable(DestinationType.Post.route) {}
        composable(DestinationType.Notifications.route) {}
        composable(DestinationType.Profile.route) {}
    }
}
