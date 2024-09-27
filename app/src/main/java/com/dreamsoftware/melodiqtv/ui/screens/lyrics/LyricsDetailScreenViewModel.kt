package com.dreamsoftware.melodiqtv.ui.screens.lyrics

import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import com.dreamsoftware.melodiqtv.domain.model.SongBO
import com.dreamsoftware.melodiqtv.domain.usecase.GetSongByIdUseCase
import com.dreamsoftware.melodiqtv.ui.utils.EMPTY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LyricsDetailScreenViewModel @Inject constructor(
    private val getSongByIdUseCase: GetSongByIdUseCase
) : FudgeTvViewModel<LyricsDetailUiState, LyricsDetailSideEffects>(), LyricsDetailActionListener {

    override fun onGetDefaultState(): LyricsDetailUiState = LyricsDetailUiState()

    fun fetchData(id: String) {
        executeUseCaseWithParams(
            useCase = getSongByIdUseCase,
            params = GetSongByIdUseCase.Params(id),
            onSuccess = ::onGetSongDetailsCompleted
        )
    }

    private fun onGetSongDetailsCompleted(songDetail: SongBO) {
        updateState {
            it.copy(
                songImageUrl = songDetail.imageUrl,
                lyrics = songDetail.lyrics.orEmpty()
            )
        }
    }

    override fun onCompleted() {
        launchSideEffect(LyricsDetailSideEffects.CloseDetail)
    }
}

data class LyricsDetailUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val songImageUrl: String = String.EMPTY,
    val lyrics: String = String.EMPTY
): UiState<LyricsDetailUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): LyricsDetailUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface LyricsDetailSideEffects : SideEffect {
    data object CloseDetail: LyricsDetailSideEffects
}