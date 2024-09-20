package com.dreamsoftware.melodiqtv.ui.screens.moreoptions

import com.dreamsoftware.melodiqtv.domain.usecase.AddFavoriteSongUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.GetSongByIdUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.RemoveFavoriteSongUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.VerifySongInFavoritesUseCase
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import com.dreamsoftware.melodiqtv.domain.model.SongBO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoreOptionsViewModel @Inject constructor(
    private val getSongByIdUseCase: GetSongByIdUseCase,
    private val addFavoriteSongUseCase: AddFavoriteSongUseCase,
    private val removeFavoriteSongUseCase: RemoveFavoriteSongUseCase,
    private val verifySongInFavoritesUseCase: VerifySongInFavoritesUseCase
) : FudgeTvViewModel<MoreOptionsUiState, MoreOptionsSideEffects>(), MoreOptionsScreenActionListener {

    override fun onGetDefaultState(): MoreOptionsUiState = MoreOptionsUiState()

    fun fetchData(id: String) {
        executeUseCaseWithParams(
            useCase = verifySongInFavoritesUseCase,
            params = VerifySongInFavoritesUseCase.Params(songId = id),
            onSuccess = ::onVerifySongInFavoritesCompleted
        )
        executeUseCaseWithParams(
            useCase = getSongByIdUseCase,
            params = GetSongByIdUseCase.Params(id),
            onSuccess = ::onGetSongByIdSuccessfully
        )
    }

    private fun onGetSongByIdSuccessfully(song: SongBO) {
        updateState { it.copy(song = song) }
    }

    override fun onBackPressed() {
        launchSideEffect(MoreOptionsSideEffects.CloseMoreOptions)
    }

    override fun onPlaySongVideoClip() {
        uiState.value.song?.let {
            launchSideEffect(
                MoreOptionsSideEffects.PlaySongVideoClip(id = it.id)
            )
        }
    }

    override fun onFavouriteClicked() {
        with(uiState.value) {
            song?.let {
                if (isFavorite) {
                    removeSongFromFavorites(id = it.id)
                } else {
                    addSongToFavorites(id = it.id)
                }
            }
        }
    }

    override fun onOpenArtistDetail() {
        uiState.value.song?.let {
            launchSideEffect(
                MoreOptionsSideEffects.OpenArtistDetail(id = it.id)
            )
        }
    }

    override fun onPlaySong() {
        uiState.value.song?.let {
            launchSideEffect(
                MoreOptionsSideEffects.PlaySong(id = it.id)
            )
        }
    }

    private fun removeSongFromFavorites(id: String) {
        executeUseCaseWithParams(
            useCase = removeFavoriteSongUseCase,
            params = RemoveFavoriteSongUseCase.Params(
                songId = id
            ),
            onSuccess = ::onChangeFavoriteSongCompleted
        )
    }

    private fun addSongToFavorites(id: String) {
        executeUseCaseWithParams(
            useCase = addFavoriteSongUseCase,
            params = AddFavoriteSongUseCase.Params(songId = id),
            onSuccess = ::onChangeFavoriteSongCompleted
        )
    }

    private fun onChangeFavoriteSongCompleted(isSuccess: Boolean) {
        if (isSuccess) {
            updateState { it.copy(isFavorite = !it.isFavorite) }
        }
    }

    private fun onVerifySongInFavoritesCompleted(isFavorite: Boolean) {
        updateState { it.copy(isFavorite = isFavorite) }
    }
}

data class MoreOptionsUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val song: SongBO? = null,
    val isFavorite: Boolean = false
) : UiState<MoreOptionsUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): MoreOptionsUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface MoreOptionsSideEffects : SideEffect {
    data class PlaySongVideoClip(val id: String) : MoreOptionsSideEffects
    data class PlaySong(val id: String) : MoreOptionsSideEffects
    data class OpenArtistDetail(val id: String): MoreOptionsSideEffects
    data object CloseMoreOptions: MoreOptionsSideEffects
}