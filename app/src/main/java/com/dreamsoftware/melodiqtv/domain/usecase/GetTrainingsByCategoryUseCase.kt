package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.melodiqtv.domain.model.ITrainingProgramBO
import com.dreamsoftware.melodiqtv.domain.repository.ISubscriptionsRepository
import com.dreamsoftware.melodiqtv.domain.repository.ITrainingRepository
import com.dreamsoftware.melodiqtv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class GetTrainingsByCategoryUseCase(
    private val userRepository: IUserRepository,
    private val subscriptionsRepository: ISubscriptionsRepository,
    private val trainingRepository: ITrainingRepository
) : FudgeTvUseCaseWithParams<GetTrainingsByCategoryUseCase.Params, List<ITrainingProgramBO>>() {
    override suspend fun onExecuted(params: Params): List<ITrainingProgramBO> {
        val userUid = userRepository.getAuthenticatedUid()
        val hasActiveSubscription = subscriptionsRepository.hasActiveSubscription(userUid)
        return trainingRepository.getTrainingsByCategory(
            id = params.id,
            includePremium = hasActiveSubscription
        ).toList()
    }

    data class Params(
        val id: String
    )
}