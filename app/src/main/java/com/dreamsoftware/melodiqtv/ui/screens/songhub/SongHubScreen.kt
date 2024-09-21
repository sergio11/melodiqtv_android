package com.dreamsoftware.melodiqtv.ui.screens.songhub

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fudge.component.FudgeTvScreen

@Composable
fun SongHubScreen(
    viewModel: SongHubViewModel = hiltViewModel(),
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
        SongHubScreenContent(
            state = uiState,
            actionListener = viewModel
        )
    }
}


