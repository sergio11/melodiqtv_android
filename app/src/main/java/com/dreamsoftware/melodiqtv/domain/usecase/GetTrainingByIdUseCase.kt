package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.melodiqtv.domain.model.ITrainingProgramBO
import com.dreamsoftware.melodiqtv.domain.model.TrainingTypeEnum
import com.dreamsoftware.melodiqtv.domain.repository.ITrainingRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class GetTrainingByIdUseCase(
    private val trainingRepository: ITrainingRepository
) : FudgeTvUseCaseWithParams<GetTrainingByIdUseCase.Params, ITrainingProgramBO>() {

    override suspend fun onExecuted(params: Params): ITrainingProgramBO = with(params) {
        trainingRepository.getTrainingById(id = id, type = type)
    }

    data class Params(
        val id: String,
        val type: TrainingTypeEnum
    )
}