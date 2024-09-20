package com.dreamsoftware.melodiqtv.ui.screens.artistdetail

import com.dreamsoftware.melodiqtv.domain.model.ArtistBO
import com.dreamsoftware.melodiqtv.domain.usecase.GetArtistDetailUseCase
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArtistDetailScreenViewModel @Inject constructor(
    private val getArtistDetailUseCase: GetArtistDetailUseCase
) : FudgeTvViewModel<ArtistDetailUiState, ArtistDetailSideEffects>(), ArtistDetailActionListener {

    override fun onGetDefaultState(): ArtistDetailUiState = ArtistDetailUiState()

    fun fetchData(id: String) {
        executeUseCaseWithParams(
            useCase = getArtistDetailUseCase,
            params = GetArtistDetailUseCase.Params(id),
            onSuccess = ::onGetArtistDetailsCompleted
        )
    }

    private fun onGetArtistDetailsCompleted(artistDetail: ArtistBO) {
        updateState { it.copy(artistDetail = artistDetail) }
    }

    override fun onBackPressed() {
        launchSideEffect(ArtistDetailSideEffects.CloseDetail)
    }
}

data class ArtistDetailUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val artistDetail: ArtistBO? = null
): UiState<ArtistDetailUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): ArtistDetailUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface ArtistDetailSideEffects : SideEffect {
    data object CloseDetail: ArtistDetailSideEffects
}