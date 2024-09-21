package com.dreamsoftware.melodiqtv.ui.screens.songdetail

import androidx.annotation.StringRes
import com.dreamsoftware.melodiqtv.R
import com.dreamsoftware.melodiqtv.domain.usecase.AddFavoriteSongUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.GetSongByIdUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.RemoveFavoriteSongUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.VerifySongInFavoritesUseCase
import com.dreamsoftware.melodiqtv.ui.screens.songdetail.SongDetailUiState.SongInfoItem
import com.dreamsoftware.melodiqtv.ui.utils.EMPTY
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import com.dreamsoftware.melodiqtv.domain.model.SongBO
import com.dreamsoftware.melodiqtv.domain.model.SongTypeEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SongDetailViewModel @Inject constructor(
    private val getSongByIdUseCase: GetSongByIdUseCase,
    private val addFavoriteSongUseCase: AddFavoriteSongUseCase,
    private val removeFavoriteSongUseCase: RemoveFavoriteSongUseCase,
    private val verifySongInFavoritesUseCase: VerifySongInFavoritesUseCase
) : FudgeTvViewModel<SongDetailUiState, SongDetailSideEffects>(), SongDetailScreenActionListener {

    override fun onGetDefaultState(): SongDetailUiState = SongDetailUiState()

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

    override fun onPlaySongVideoClip() {
        with(uiState.value) {
            launchSideEffect(SongDetailSideEffects.PlayingSongVideoClip(
                id = id
            ))
        }
    }

    override fun onOpenMoreInfo() {
        with(uiState.value) {
            launchSideEffect(SongDetailSideEffects.OpenMoreInfo(
                id = id
            ))
        }
    }

    override fun onSongFavoriteClicked() {
        with(uiState.value) {
            if(isFavorite) {
                executeUseCaseWithParams(
                    useCase = removeFavoriteSongUseCase,
                    params = RemoveFavoriteSongUseCase.Params(
                        songId = id
                    ),
                    onSuccess = ::onChangeFavoriteSongCompleted
                )
            } else {
                executeUseCaseWithParams(
                    useCase = addFavoriteSongUseCase,
                    params = AddFavoriteSongUseCase.Params(
                        songId = id
                    ),
                    onSuccess = ::onChangeFavoriteSongCompleted
                )
            }
        }
    }

    private fun onChangeFavoriteSongCompleted(isSuccess: Boolean) {
        if(isSuccess) {
            updateState { it.copy(isFavorite = !it.isFavorite) }
        }
    }

    private fun onVerifySongInFavoritesCompleted(isFavorite: Boolean) {
        updateState { it.copy(isFavorite = isFavorite) }
    }

    private fun onGetSongByIdSuccessfully(song: SongBO) {
        updateState {
            with(song) {
                it.copy(
                    subtitle = "$artistName | ${type.value}",
                    title = title,
                    description = description,
                    id = id,
                    itemsInfo = buildList {
                        add(SongInfoItem(info = "$duration min", labelRes = R.string.song_detail_song_duration_label_text))
                    },
                    imageUrl = imageUrl
                )
            }
        }
    }
}

data class SongDetailUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val subtitle: String = String.EMPTY,
    val title: String = String.EMPTY,
    val description: String = String.EMPTY,
    val imageUrl: String = String.EMPTY,
    val id: String = String.EMPTY,
    val type: SongTypeEnum = SongTypeEnum.LIVE,
    val itemsInfo: List<SongInfoItem> = listOf(),
    val isFavorite: Boolean = false
): UiState<SongDetailUiState>(isLoading, errorMessage) {

    override fun copyState(isLoading: Boolean, errorMessage: String?): SongDetailUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)

    data class SongInfoItem(
        val info: String = String.EMPTY,
        @StringRes val labelRes: Int
    )
}

sealed interface SongDetailSideEffects: SideEffect {
    data class PlayingSongVideoClip(val id: String): SongDetailSideEffects
    data class OpenMoreInfo(val id: String): SongDetailSideEffects
}
