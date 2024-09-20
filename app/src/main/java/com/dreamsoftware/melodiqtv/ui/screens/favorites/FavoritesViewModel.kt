package com.dreamsoftware.melodiqtv.ui.screens.favorites

import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.IFudgeTvErrorMapper
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import com.dreamsoftware.melodiqtv.di.FavoritesScreenErrorMapper
import com.dreamsoftware.melodiqtv.domain.model.SongBO
import com.dreamsoftware.melodiqtv.domain.usecase.GetFavoritesSongsByUserUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.RemoveFavoriteSongUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesSongsByUserUseCase: GetFavoritesSongsByUserUseCase,
    private val removeFavoriteSongUseCase: RemoveFavoriteSongUseCase,
    @FavoritesScreenErrorMapper private val errorMapper: IFudgeTvErrorMapper,
) : FudgeTvViewModel<FavoritesUiState, FavoritesSideEffects>(), FavoritesScreenActionListener {

    override fun onGetDefaultState(): FavoritesUiState = FavoritesUiState()

    fun fetchData() {
        executeUseCase(
            useCase = getFavoritesSongsByUserUseCase,
            onSuccess = ::onGetFavoritesSongsSuccessfully,
            onMapExceptionToState = ::onMapExceptionToState
        )
    }

    override fun onSongSelected(song: SongBO) {
        updateState { it.copy(songSelected = song) }
    }

    override fun onOpenSongDetail(id: String) {
        uiState.value.songSelected?.let { trainingProgramSelected ->
            updateState { it.copy(songSelected = null) }
            launchSideEffect(FavoritesSideEffects.OpenSongDetail(
                id = trainingProgramSelected.id,
            ))
        }
    }

    override fun onSongRemovedFromFavorites(id: String) {
        executeUseCaseWithParams(
            useCase = removeFavoriteSongUseCase,
            params = RemoveFavoriteSongUseCase.Params(songId = id),
            onSuccess = ::onSongRemovedFromFavoritesCompletedSuccessfully
        )
    }

    override fun onDismissRequest() {
        updateState { it.copy(songSelected = null) }
    }

    private fun onGetFavoritesSongsSuccessfully(songList: List<SongBO>) {
        updateState { it.copy(favoriteSongs = songList) }
    }

    private fun onSongRemovedFromFavoritesCompletedSuccessfully(isRemoved: Boolean) {
        if(isRemoved) {
            updateState { it.copy(
                favoriteSongs = it.favoriteSongs.filterNot { training -> training.id == it.songSelected?.id },
                songSelected = null
            ) }
        }
    }

    private fun onMapExceptionToState(ex: Exception, uiState: FavoritesUiState) =
        uiState.copy(
            isLoading = false,
            errorMessage = errorMapper.mapToMessage(ex)
        )
}

data class FavoritesUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val favoriteSongs: List<SongBO> = emptyList(),
    val songSelected: SongBO? = null
): UiState<FavoritesUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): FavoritesUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface FavoritesSideEffects: SideEffect {
    data class OpenSongDetail(val id: String): FavoritesSideEffects
}