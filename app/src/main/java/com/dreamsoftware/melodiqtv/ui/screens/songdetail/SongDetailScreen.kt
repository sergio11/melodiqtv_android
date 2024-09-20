package com.dreamsoftware.melodiqtv.ui.screens.songdetail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fudge.component.FudgeTvScreen

data class SongDetailScreenArgs(
    val id: String
)

@Composable
fun SongDetailScreen(
    viewModel: SongDetailViewModel = hiltViewModel(),
    args: SongDetailScreenArgs,
    onPlaySongVideoClip: (String) -> Unit,
    onOpenMoreDetails: (String) -> Unit,
    onBackPressed: () -> Unit
) {
    FudgeTvScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { SongDetailUiState() },
        onSideEffect = {
            when(it) {
                is SongDetailSideEffects.PlayingSongVideoClip -> onPlaySongVideoClip(it.id)
                is SongDetailSideEffects.OpenMoreInfo -> onOpenMoreDetails(it.id)
            }
        },
        onInit = {
            fetchData(id = args.id)
        }
    ) { uiState ->
        SongDetailScreenContent(
            state = uiState,
            actionListener = viewModel
        )
    }
}