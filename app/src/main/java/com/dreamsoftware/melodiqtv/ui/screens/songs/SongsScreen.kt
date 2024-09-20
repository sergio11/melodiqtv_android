package com.dreamsoftware.melodiqtv.ui.screens.songs

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fudge.component.FudgeTvScreen

@Composable
fun SongsScreen(
    viewModel: SongViewModel = hiltViewModel(),
    onOpenSongDetail: (String) -> Unit
) {
    FudgeTvScreen(
        viewModel = viewModel,
        onInitialUiState = { SongsUiState() },
        onSideEffect = {
            when(it) {
                is SongsSideEffects.OpenSongDetail -> onOpenSongDetail(it.id)
            }
        },
        onInit = {
            fetchData()
        }
    ) { uiState ->
        SongsScreenContent(
            state = uiState,
            actionListener = viewModel
        )
    }
}


