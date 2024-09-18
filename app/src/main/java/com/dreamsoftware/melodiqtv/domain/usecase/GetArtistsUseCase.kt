package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.melodiqtv.domain.model.ArtistBO
import com.dreamsoftware.melodiqtv.domain.repository.IArtistRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCase

class GetArtistsUseCase(
    private val artistRepository: IArtistRepository
) : FudgeTvUseCase<List<ArtistBO>>() {
    override suspend fun onExecuted():  List<ArtistBO> =
        artistRepository.getArtists()
}