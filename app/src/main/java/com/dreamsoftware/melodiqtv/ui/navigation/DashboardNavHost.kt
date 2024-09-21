package com.dreamsoftware.melodiqtv.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dreamsoftware.melodiqtv.ui.screens.category.CategoryDetailScreen
import com.dreamsoftware.melodiqtv.ui.screens.favorites.FavoritesScreen
import com.dreamsoftware.melodiqtv.ui.screens.home.HomeScreen
import com.dreamsoftware.melodiqtv.ui.screens.artistdetail.ArtistDetailScreen
import com.dreamsoftware.melodiqtv.ui.screens.moreoptions.MoreOptionsScreen
import com.dreamsoftware.melodiqtv.ui.screens.player.audio.AudioPlayerScreen
import com.dreamsoftware.melodiqtv.ui.screens.player.video.VideoPlayerScreen
import com.dreamsoftware.melodiqtv.ui.screens.settings.SettingsScreen
import com.dreamsoftware.melodiqtv.ui.screens.subscription.SubscriptionScreen
import com.dreamsoftware.melodiqtv.ui.screens.songhub.SongHubScreen
import com.dreamsoftware.melodiqtv.ui.screens.songdetail.SongDetailScreen

@Composable
fun DashboardNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(Screen.Home.route) {
            with(navController) {
                HomeScreen(
                    onOpenCategory = { id ->
                        navigate(Screen.CategoryDetail.buildRoute(id))
                    },
                    onGoToSubscriptions = {
                        navigate(Screen.Subscription.route)
                    },
                    onOpenSongDetail = { id ->
                        navigate(Screen.SongDetail.buildRoute(id))
                    }
                )
            }
        }
        composable(Screen.Songs.route) {
            SongHubScreen(
                onOpenSongDetail = { id ->
                    navController.navigate(Screen.SongDetail.buildRoute(id))
                }
            )
        }
        composable(Screen.Favorite.route) {
            with(navController) {
                FavoritesScreen(
                    onBackPressed = {
                        popBackStack()
                    },
                    onOpenSongDetail = { id ->
                        navigate(Screen.SongDetail.buildRoute(id))
                    }
                )
            }
        }

        composable(Screen.CategoryDetail.route) { navBackStackEntry ->
            navBackStackEntry.arguments?.let(Screen.CategoryDetail::parseArgs)?.let { args ->
                with(navController) {
                    CategoryDetailScreen(
                        args = args,
                        onOpenSongDetail = { id ->
                            navigate(Screen.SongDetail.buildRoute(id))
                        },
                        onBackPressed = {
                            popBackStack()
                        }
                    )
                }
            }
        }

        composable(Screen.VideoPlayer.route) { navBackStackEntry ->
            navBackStackEntry.arguments?.let(Screen.VideoPlayer::parseArgs)?.let { args ->
                with(navController) {
                    VideoPlayerScreen(args = args) {
                        popBackStack()
                    }
                }
            }
        }
        composable(Screen.AudioPlayer.route) { navBackStackEntry ->
            navBackStackEntry.arguments?.let(Screen.AudioPlayer::parseArgs)?.let { args ->
                with(navController) {
                    AudioPlayerScreen(args = args) {
                        popBackStack()
                    }
                }
            }
        }
        composable(Screen.Settings.route) {
            with(navController) {
                SettingsScreen(
                    onGoToSubscriptions = {
                        navigate(Screen.Subscription.route)
                    },
                    onBackPressed = {
                        popBackStack()
                    }
                )
            }
        }
        composable(Screen.SongDetail.route) { navBackStackEntry ->
            navBackStackEntry.arguments?.let(Screen.SongDetail::parseArgs)?.let { args ->
                with(navController) {
                    SongDetailScreen(
                        args = args,
                        onPlaySongVideoClip = { id ->
                            navigate(Screen.VideoPlayer.buildRoute(id))
                        },
                        onOpenMoreDetails = { id ->
                            navigate(Screen.MoreOptions.buildRoute(id))
                        },
                        onBackPressed = {
                            popBackStack()
                        }
                    )
                }
            }
        }
        composable(Screen.MoreOptions.route) { navBackStackEntry ->
            navBackStackEntry.arguments?.let(Screen.MoreOptions::parseArgs)?.let { args ->
                with(navController) {
                    MoreOptionsScreen(
                        args = args,
                        onPlayVideoClip = { id ->
                            navigate(Screen.VideoPlayer.buildRoute(id))
                        },
                        onPlaySong = {
                            navigate(Screen.AudioPlayer.buildRoute(it))
                        },
                        onOpenArtistDetail = {
                            navigate(Screen.ArtistDetail.buildRoute(it))
                        },
                        onBackPressed = {
                            popBackStack()
                        },
                    )
                }
            }
        }

        composable(Screen.ArtistDetail.route) { navBackStackEntry ->
            navBackStackEntry.arguments?.let(Screen.ArtistDetail::parseArgs)?.let { args ->
                with(navController) {
                    ArtistDetailScreen(
                        args = args,
                        onBackPressed = {
                            popBackStack()
                        },
                    )
                }
            }
        }
        composable(Screen.Subscription.route) {
            with(navController) {
                SubscriptionScreen(
                    onBackPressed = {
                        popBackStack()
                    }
                )
            }
        }
    }
}