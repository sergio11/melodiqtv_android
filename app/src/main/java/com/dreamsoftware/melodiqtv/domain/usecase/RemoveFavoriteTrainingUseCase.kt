package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.melodiqtv.domain.repository.ITrainingRepository
import com.dreamsoftware.melodiqtv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class RemoveFavoriteTrainingUseCase(
    private val userRepository: IUserRepository,
    private val trainingRepository: ITrainingRepository
): FudgeTvUseCaseWithParams<RemoveFavoriteTrainingUseCase.Params, Boolean>() {

    override suspend fun onExecuted(params: Params) = with(params) {
        trainingRepository.removeFavoriteTraining(
            profileId = userRepository.getAuthenticatedUid(),
            trainingId = trainingId
        )
    }

    data class Params(
        val trainingId: String
    )
}