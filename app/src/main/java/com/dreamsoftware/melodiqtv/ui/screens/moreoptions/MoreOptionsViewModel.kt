package com.dreamsoftware.melodiqtv.ui.screens.moreoptions

import com.dreamsoftware.melodiqtv.domain.model.ITrainingProgramBO
import com.dreamsoftware.melodiqtv.domain.model.TrainingTypeEnum
import com.dreamsoftware.melodiqtv.domain.usecase.AddFavoriteSongUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.GetSongByIdUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.RemoveFavoriteSongUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.VerifySongInFavoritesUseCase
import com.dreamsoftware.melodiqtv.ui.utils.toTrainingType
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
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

    fun fetchData(id: String, type: TrainingTypeEnum) {
        executeUseCaseWithParams(
            useCase = verifySongInFavoritesUseCase,
            params = VerifySongInFavoritesUseCase.Params(songId = id),
            onSuccess = ::onVerifyTrainingInFavoritesCompleted
        )
        executeUseCaseWithParams(
            useCase = getSongByIdUseCase,
            params = GetSongByIdUseCase.Params(id, type),
            onSuccess = ::onGetTrainingProgramByIdSuccessfully
        )
    }

    private fun onGetTrainingProgramByIdSuccessfully(trainingProgram: ITrainingProgramBO) {
        updateState { it.copy(trainingProgram = trainingProgram) }
    }

    override fun onBackPressed() {
        launchSideEffect(MoreOptionsSideEffects.ExitFromMoreDetail)
    }

    override fun onTrainingProgramOpened() {
        uiState.value.trainingProgram?.let {
            launchSideEffect(
                MoreOptionsSideEffects.PlayTrainingProgram(
                    id = it.id,
                    type = it.toTrainingType()
                )
            )
        }
    }

    override fun onFavouriteClicked() {
        with(uiState.value) {
            trainingProgram?.let {
                if (isFavorite) {
                    removeTrainingProgramFromFavorites(id = it.id)
                } else {
                    addTrainingProgramToFavorites(id = it.id, type = it.toTrainingType())
                }
            }
        }
    }

    override fun onOpenInstructorDetail() {
        uiState.value.trainingProgram?.let {
            launchSideEffect(
                MoreOptionsSideEffects.OpenInstructorDetail(id = it.instructorId)
            )
        }
    }

    override fun onPlayTrainingSong() {
        uiState.value.trainingProgram?.let {
            launchSideEffect(
                MoreOptionsSideEffects.PlayTrainingSong(songId = it.song)
            )
        }
    }

    private fun removeTrainingProgramFromFavorites(id: String) {
        executeUseCaseWithParams(
            useCase = removeFavoriteSongUseCase,
            params = RemoveFavoriteSongUseCase.Params(
                songId = id
            ),
            onSuccess = ::onChangeFavoriteTrainingCompleted
        )
    }

    private fun addTrainingProgramToFavorites(id: String, type: TrainingTypeEnum) {
        executeUseCaseWithParams(
            useCase = addFavoriteSongUseCase,
            params = AddFavoriteSongUseCase.Params(
                trainingId = id,
                trainingType = type
            ),
            onSuccess = ::onChangeFavoriteTrainingCompleted
        )
    }

    private fun onChangeFavoriteTrainingCompleted(isSuccess: Boolean) {
        if (isSuccess) {
            updateState { it.copy(isFavorite = !it.isFavorite) }
        }
    }

    private fun onVerifyTrainingInFavoritesCompleted(isFavorite: Boolean) {
        updateState { it.copy(isFavorite = isFavorite) }
    }
}

data class MoreOptionsUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val trainingProgram: ITrainingProgramBO? = null,
    val isFavorite: Boolean = false
) : UiState<MoreOptionsUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): MoreOptionsUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface MoreOptionsSideEffects : SideEffect {
    data class PlayTrainingProgram(val id: String, val type: TrainingTypeEnum) : MoreOptionsSideEffects
    data class PlayTrainingSong(val songId: String) : MoreOptionsSideEffects
    data class OpenInstructorDetail(val id: String): MoreOptionsSideEffects
    data object ExitFromMoreDetail: MoreOptionsSideEffects
}