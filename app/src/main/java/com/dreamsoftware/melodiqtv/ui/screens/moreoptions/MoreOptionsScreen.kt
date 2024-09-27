package com.dreamsoftware.melodiqtv.ui.screens.moreoptions

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fudge.component.FudgeTvScreen

data class MoreOptionsScreenArgs(
    val id: String
)

@Composable
fun MoreOptionsScreen(
    viewModel: MoreOptionsViewModel = hiltViewModel(),
    args: MoreOptionsScreenArgs,
    onBackPressed: () -> Unit,
    onOpenArtistDetail: (id: String) -> Unit,
    onOpenSongLyricsDetail: (id: String) -> Unit,
    onPlayVideoClip: (id: String) -> Unit,
    onPlaySong: (id: String) -> Unit
) {
    FudgeTvScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { MoreOptionsUiState() },
        onSideEffect = {
            when(it) {
                MoreOptionsSideEffects.CloseMoreOptions -> onBackPressed()
                is MoreOptionsSideEffects.PlaySongVideoClip -> onPlayVideoClip(it.id)
                is MoreOptionsSideEffects.PlaySong -> onPlaySong(it.id)
                is MoreOptionsSideEffects.OpenArtistDetail -> onOpenArtistDetail(it.id)
                is MoreOptionsSideEffects.OpenSongLyricsDetail -> onOpenSongLyricsDetail(it.id)
            }
        },
        onInit = {
            with(args) {
                fetchData(id = id)
            }
        }
    ) { uiState ->
        MoreOptionsScreenContent(
            state = uiState,
            actionListener = viewModel
        )
    }
}

