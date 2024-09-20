package com.dreamsoftware.melodiqtv.ui.screens.player.video

import com.dreamsoftware.melodiqtv.domain.usecase.GetSongByIdUseCase
import com.dreamsoftware.melodiqtv.ui.utils.EMPTY
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import com.dreamsoftware.melodiqtv.domain.model.SongBO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoPlayerViewModel @Inject constructor(
    private val getSongByIdUseCase: GetSongByIdUseCase
) : FudgeTvViewModel<VideoPlayerUiState, VideoPlayerSideEffects>() {

    override fun onGetDefaultState(): VideoPlayerUiState = VideoPlayerUiState()

    fun fetchData(id: String) {
        executeUseCaseWithParams(
            useCase = getSongByIdUseCase,
            params = GetSongByIdUseCase.Params(id),
            onSuccess = ::onGetSongByIdSuccessfully
        )
    }

    private fun onGetSongByIdSuccessfully(song: SongBO) {
        updateState {
            with(song) {
                it.copy(
                    title = title,
                    artist = artistName,
                    videoUrl = videoUrl,
                    id = id
                )
            }
        }
    }
}

data class VideoPlayerUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val id: String = String.EMPTY,
    val videoUrl: String = String.EMPTY,
    val title: String = String.EMPTY,
    val artist: String = String.EMPTY
): UiState<VideoPlayerUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): VideoPlayerUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface VideoPlayerSideEffects: SideEffect