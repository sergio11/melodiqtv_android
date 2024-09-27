package com.dreamsoftware.melodiqtv.ui.screens.lyrics

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fudge.component.FudgeTvScreen

data class LyricsDetailScreenArgs(
    val id: String
)

@Composable
fun LyricsDetailScreen(
    viewModel: LyricsDetailScreenViewModel = hiltViewModel(),
    args: LyricsDetailScreenArgs,
    onBackPressed: () -> Unit
) {
    FudgeTvScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { LyricsDetailUiState() },
        onSideEffect = {
            when(it) {
                LyricsDetailSideEffects.CloseDetail -> onBackPressed()
            }
        },
        onInit = {
            fetchData(args.id)
        }
    ) { uiState ->
        LyricsDetailScreenContent(
            uiState = uiState,
            actionListener = viewModel
        )
    }
}