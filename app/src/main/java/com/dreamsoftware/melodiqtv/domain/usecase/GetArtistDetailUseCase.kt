package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.melodiqtv.domain.model.ArtistBO
import com.dreamsoftware.melodiqtv.domain.repository.IArtistRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class GetArtistDetailUseCase(
    private val artistRepository: IArtistRepository
): FudgeTvUseCaseWithParams<GetArtistDetailUseCase.Params, ArtistBO>() {

    override suspend fun onExecuted(params: Params): ArtistBO =
        artistRepository.getArtistById(params.id)

    data class Params(
        val id: String
    )
}