package com.dreamsoftware.melodiqtv.ui.screens.favorites

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fudge.component.FudgeTvScreen

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    onOpenSongDetail: (id: String) -> Unit
) {
    FudgeTvScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { FavoritesUiState() },
        onSideEffect = {
            when(it) {
                is FavoritesSideEffects.OpenSongDetail -> onOpenSongDetail(it.id)
            }
        },
        onInit = {
            fetchData()
        }
    ) { uiState ->
        FavoritesScreenContent(
            uiState = uiState,
            actionListener = viewModel
        )
    }
}