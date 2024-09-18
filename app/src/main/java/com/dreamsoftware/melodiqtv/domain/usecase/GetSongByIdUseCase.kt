package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.melodiqtv.domain.repository.ISongRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams
import com.dreamsoftware.melodiqtv.domain.model.SongBO

class GetSongByIdUseCase(
    private val songRepository: ISongRepository
) : FudgeTvUseCaseWithParams<GetSongByIdUseCase.Params, SongBO>() {

    override suspend fun onExecuted(params: Params): SongBO = with(params) {
        songRepository.getSongById(id = id)
    }

    data class Params(
        val id: String
    )
}