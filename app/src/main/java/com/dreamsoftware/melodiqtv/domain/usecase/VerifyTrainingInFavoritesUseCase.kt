package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.melodiqtv.domain.repository.IProfilesRepository
import com.dreamsoftware.melodiqtv.domain.repository.ITrainingRepository
import com.dreamsoftware.melodiqtv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class VerifyTrainingInFavoritesUseCase(
    private val userRepository: IUserRepository,
    private val profileRepository: IProfilesRepository,
    private val trainingRepository: ITrainingRepository
): FudgeTvUseCaseWithParams<VerifyTrainingInFavoritesUseCase.Params, Boolean>() {

    override suspend fun onExecuted(params: Params) = with(params) {
        val userUid = userRepository.getAuthenticatedUid()
        val profileSelected = profileRepository.getProfileSelectedByUser(userUid)
        trainingRepository.hasTrainingInFavorites(
            profileId = profileSelected.uuid,
            trainingId = trainingId
        )
    }

    data class Params(
        val trainingId: String
    )
}