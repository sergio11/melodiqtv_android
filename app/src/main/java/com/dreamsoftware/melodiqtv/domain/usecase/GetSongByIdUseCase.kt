package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.melodiqtv.domain.model.TrainingSongBO
import com.dreamsoftware.melodiqtv.domain.repository.ITrainingSongsRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class GetSongByIdUseCase(
    private val trainingSongRepository: ITrainingSongsRepository
) : FudgeTvUseCaseWithParams<GetSongByIdUseCase.Params, TrainingSongBO>() {

    override suspend fun onExecuted(params: Params): TrainingSongBO =
        trainingSongRepository.getSongById(params.id)

    data class Params(
        val id: String
    )
}