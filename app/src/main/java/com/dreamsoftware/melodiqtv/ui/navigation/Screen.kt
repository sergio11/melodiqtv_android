package com.dreamsoftware.melodiqtv.ui.navigation

import android.os.Bundle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.dreamsoftware.melodiqtv.ui.screens.category.CategoryDetailScreenArgs
import com.dreamsoftware.melodiqtv.ui.screens.artistdetail.ArtistDetailScreenArgs
import com.dreamsoftware.melodiqtv.ui.screens.moreoptions.MoreOptionsScreenArgs
import com.dreamsoftware.melodiqtv.ui.screens.player.audio.AudioPlayerScreenArgs
import com.dreamsoftware.melodiqtv.ui.screens.player.video.VideoPlayerScreenArgs
import com.dreamsoftware.melodiqtv.ui.screens.profiles.changesecurepin.ChangeSecurePinScreenArgs
import com.dreamsoftware.melodiqtv.ui.screens.profiles.delete.DeleteProfileScreenArgs
import com.dreamsoftware.melodiqtv.ui.screens.profiles.save.SaveProfileScreenArgs
import com.dreamsoftware.melodiqtv.ui.screens.profiles.secure.SecurePinScreenArgs
import com.dreamsoftware.melodiqtv.ui.screens.songdetail.SongDetailScreenArgs

sealed class Screen(
    val route: String,
    val name: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {

    data object Splash: Screen(route = "splash", name = "Splash")
    data object Onboarding: Screen(route = "onboarding", name = "Onboarding")
    data object SignIn: Screen(route = "sign_in", name = "SignIn")
    data object SignUp: Screen(route = "sign_up", name = "SignUp")
    data object Dashboard: Screen(route = "dashboard", name = "Dashboard")
    data object Subscription: Screen(route = "subscription", name = "Subscription")
    data object Profiles: Screen(route = "profiles", name = "Profiles")
    data object ProfileSelector: Screen(route = "profile_selector", name = "ProfileSelector")
    data object ProfilesManagement: Screen(route = "profile_management", name = "ProfilesManagement")
    data object AddProfile: Screen(route = "add_profile", name = "AddProfile")
    data object EditProfile: Screen(route = "edit_profile/{id}", name = "EditProfile", arguments = listOf(
        navArgument("id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(id: String): String =
            route.replace(
                oldValue = "{id}",
                newValue = id
            )

        fun parseArgs(args: Bundle): SaveProfileScreenArgs? = with(args) {
            getString("id")?.let { id ->
                SaveProfileScreenArgs(
                    profileId = id,
                )
            }
        }
    }
    data object ProfileAdvance: Screen(route = "profile_advance/{id}", name = "ProfileAdvance", arguments = listOf(
        navArgument("id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(id: String): String =
            route.replace(
                oldValue = "{id}",
                newValue = id
            )

        fun parseArgs(args: Bundle): ChangeSecurePinScreenArgs? = with(args) {
            getString("id")?.let { id ->
                ChangeSecurePinScreenArgs(
                    profileId = id,
                )
            }
        }
    }
    data object DeleteProfile: Screen(route = "delete_profile/{id}", name = "DeleteProfile", arguments = listOf(
        navArgument("id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(id: String): String =
            route.replace(
                oldValue = "{id}",
                newValue = id
            )

        fun parseArgs(args: Bundle): DeleteProfileScreenArgs? = with(args) {
            getString("id")?.let { id ->
                DeleteProfileScreenArgs(
                    profileId = id,
                )
            }
        }
    }
    data object UnlockProfile: Screen(route = "unlock_profile/{id}", name = "UnlockProfile", arguments = listOf(
        navArgument("id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(id: String): String =
            route.replace(
                oldValue = "{id}",
                newValue = id
            )

        fun parseArgs(args: Bundle): SecurePinScreenArgs? = with(args) {
            getString("id")?.let { id ->
                SecurePinScreenArgs(id)
            }
        }
    }

    data object Home: Screen(route = "home", name = "Home")
    data object Songs: Screen(route = "songs", name = "Songs")
    data object Favorite: Screen(route = "favorite", name = "Favorite")
    data object Settings: Screen(route = "settings", name = "Settings")
    data object VideoPlayer: Screen(route = "video_player/{id}", name = "VideoPlayer", arguments = listOf(
        navArgument("id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(id: String): String =
            route.replace(
                oldValue = "{id}",
                newValue = id
            )

        fun parseArgs(args: Bundle): VideoPlayerScreenArgs? = with(args) {
            getString("id")?.let { id ->
                VideoPlayerScreenArgs(id = id)
            }
        }
    }
    data object AudioPlayer: Screen(route = "audio_player/{id}", name = "AudioPlayer", arguments = listOf(
        navArgument("id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(id: String): String =
            route.replace(
                oldValue = "{id}",
                newValue = id
            )

        fun parseArgs(args: Bundle): AudioPlayerScreenArgs? = with(args) {
            getString("id")?.let { id ->
                AudioPlayerScreenArgs(
                    id = id
                )
            }
        }
    }
    data object SongDetail : Screen(route = "song_detail/{id}", name = "SongDetail", arguments = listOf(
        navArgument("id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(id: String): String =
            route.replace(
                oldValue = "{id}",
                newValue = id
            )

        fun parseArgs(args: Bundle): SongDetailScreenArgs? = with(args) {
            getString("id")?.let { id ->
                SongDetailScreenArgs(id = id)
            }
        }
    }

    data object CategoryDetail : Screen(route = "category_detail/{id}", name = "CategoryDetail", arguments = listOf(
        navArgument("id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(id: String): String =
            route.replace(
                oldValue = "{id}",
                newValue = id
            )

        fun parseArgs(args: Bundle): CategoryDetailScreenArgs? = with(args) {
            getString("id")?.let { id ->
                CategoryDetailScreenArgs(id = id)
            }
        }
    }

    data object ArtistDetail : Screen(route = "artist_detail/{id}", name = "ArtistDetail", arguments = listOf(
        navArgument("id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(id: String): String =
            route.replace(
                oldValue = "{id}",
                newValue = id
            )

        fun parseArgs(args: Bundle): ArtistDetailScreenArgs? = with(args) {
            getString("id")?.let { id ->
                ArtistDetailScreenArgs(id = id)
            }
        }
    }

    data object MoreOptions : Screen(route = "more_options/{id}", name = "MoreOptions", arguments = listOf(
        navArgument("id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(id: String): String =
            route.replace(
                oldValue = "{id}",
                newValue = id
            )

        fun parseArgs(args: Bundle): MoreOptionsScreenArgs? = with(args) {
            getString("id")?.let { id ->
                MoreOptionsScreenArgs(
                    id = id
                )
            }
        }
    }
}