package com.dreamsoftware.melodiqtv.ui.screens.artistdetail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fudge.component.FudgeTvScreen

data class ArtistDetailScreenArgs(
    val id: String
)

@Composable
fun ArtistDetailScreen(
    viewModel: ArtistDetailScreenViewModel = hiltViewModel(),
    args: ArtistDetailScreenArgs,
    onBackPressed: () -> Unit
) {
    FudgeTvScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { ArtistDetailUiState() },
        onSideEffect = {
            when(it) {
                ArtistDetailSideEffects.ExitFromArtistDetail -> onBackPressed()
            }
        },
        onInit = {
            fetchData(args.id)
        }
    ) { uiState ->
        ArtistDetailScreenContent(
            uiState = uiState,
            actionListener = viewModel
        )
    }
}