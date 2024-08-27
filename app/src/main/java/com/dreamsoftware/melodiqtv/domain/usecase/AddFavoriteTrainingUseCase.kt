package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.melodiqtv.domain.model.AddFavoriteTrainingBO
import com.dreamsoftware.melodiqtv.domain.model.TrainingTypeEnum
import com.dreamsoftware.melodiqtv.domain.repository.IProfilesRepository
import com.dreamsoftware.melodiqtv.domain.repository.ITrainingRepository
import com.dreamsoftware.melodiqtv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class AddFavoriteTrainingUseCase(
    private val userRepository: IUserRepository,
    private val profileRepository: IProfilesRepository,
    private val trainingRepository: ITrainingRepository
) : FudgeTvUseCaseWithParams<AddFavoriteTrainingUseCase.Params, Boolean>() {

    override suspend fun onExecuted(params: Params) = with(params) {
        val currentUserUid = userRepository.getAuthenticatedUid()
        val profileSelected = profileRepository.getProfileSelectedByUser(currentUserUid)
        toAddFavoriteTrainingBO(profileSelected.uuid)
            .let { trainingRepository.addFavoriteTraining(it) }
    }

    private fun Params.toAddFavoriteTrainingBO(profileId: String) = AddFavoriteTrainingBO(
        profileId = profileId,
        trainingId = trainingId,
        trainingType = trainingType
    )

    data class Params(
        val trainingId: String,
        val trainingType: TrainingTypeEnum
    )
}