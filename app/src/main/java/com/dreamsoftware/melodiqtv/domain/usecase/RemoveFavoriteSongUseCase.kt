package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.melodiqtv.domain.repository.ISongRepository
import com.dreamsoftware.melodiqtv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class RemoveFavoriteSongUseCase(
    private val userRepository: IUserRepository,
    private val songRepository: ISongRepository
): FudgeTvUseCaseWithParams<RemoveFavoriteSongUseCase.Params, Boolean>() {

    override suspend fun onExecuted(params: Params) = with(params) {
        songRepository.removeFavoriteSong(
            profileId = userRepository.getAuthenticatedUid(),
            songId = songId
        )
    }

    data class Params(
        val songId: String
    )
}