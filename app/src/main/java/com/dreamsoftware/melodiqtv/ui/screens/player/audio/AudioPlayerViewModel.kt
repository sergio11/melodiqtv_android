package com.dreamsoftware.melodiqtv.ui.screens.player.audio

import com.dreamsoftware.melodiqtv.ui.utils.EMPTY
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import com.dreamsoftware.melodiqtv.domain.model.SongBO
import com.dreamsoftware.melodiqtv.domain.usecase.GetSongByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AudioPlayerViewModel @Inject constructor(
    private val getSongByIdUseCase: GetSongByIdUseCase
) : FudgeTvViewModel<AudioPlayerUiState, AudioPlayerSideEffects>() {

    override fun onGetDefaultState(): AudioPlayerUiState = AudioPlayerUiState()

    fun fetchData(songId: String) {
        executeUseCaseWithParams(
            useCase = getSongByIdUseCase,
            params = GetSongByIdUseCase.Params(songId),
            onSuccess = ::onGetSongByIdSuccessfully
        )
    }

    private fun onGetSongByIdSuccessfully(song: SongBO) {
        updateState {
            with(song) {
                it.copy(
                    title = title,
                    category = category,
                    album = album.orEmpty(),
                    artistName = artistName,
                    audioUrl = audioUrl,
                    id = id,
                    imageUrl = imageUrl,
                )
            }
        }
    }
}

data class AudioPlayerUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val id: String = String.EMPTY,
    val audioUrl: String = String.EMPTY,
    val title: String = String.EMPTY,
    val category: String = String.EMPTY,
    val album: String = String.EMPTY,
    val artistName: String = String.EMPTY,
    val imageUrl: String = String.EMPTY,
): UiState<AudioPlayerUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): AudioPlayerUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface AudioPlayerSideEffects: SideEffect