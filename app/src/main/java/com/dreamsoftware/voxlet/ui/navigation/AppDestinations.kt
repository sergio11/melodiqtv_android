package com.dreamsoftware.voxlet.ui.navigation

import com.dreamsoftware.voxlet.R

enum class DestinationType(val route: String) {
    Login("login"),
    Feed("feed"),
    Search("search"),
    Post("post"),
    Notifications("notifications"),
    Profile("profile"),
    ProfileEdit("profileEdit"),
    LoginNavigation("loginNavigation"),
    HomeNavigation("homeNavigation")
}

data class AppDestination(
    val type: DestinationType,
    val resourceId: Pair<Int, Int>? = null
)

object AppDestinations {

    val destinations by lazy {
        listOf(
            AppDestination(DestinationType.Login),
            AppDestination(DestinationType.Feed, Pair(R.drawable.ic_home, R.drawable.ic_home_outlined)),
            AppDestination(DestinationType.Search, Pair(R.drawable.ic_search, R.drawable.ic_search)),
            AppDestination(DestinationType.Post, Pair(R.drawable.ic_post, R.drawable.ic_post)),
            AppDestination(DestinationType.Notifications, Pair(R.drawable.ic_heart, R.drawable.ic_heart_outlined)),
            AppDestination(DestinationType.Profile, Pair(R.drawable.ic_profile, R.drawable.ic_profile_outlined)),
            AppDestination(DestinationType.ProfileEdit),
            AppDestination(DestinationType.LoginNavigation),
            AppDestination(DestinationType.HomeNavigation)
        )
    }

    val screenItems by lazy {
        listOf(
            getDestinationByType(DestinationType.Feed),
            getDestinationByType(DestinationType.Search),
            getDestinationByType(DestinationType.Post),
            getDestinationByType(DestinationType.Notifications),
            getDestinationByType(DestinationType.Profile)
        )
    }

    private fun getDestinationByType(type: DestinationType): AppDestination =
        destinations.first { it.type == type }
}

