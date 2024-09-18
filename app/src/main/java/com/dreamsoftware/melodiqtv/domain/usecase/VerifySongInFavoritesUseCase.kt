package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.melodiqtv.domain.repository.IProfilesRepository
import com.dreamsoftware.melodiqtv.domain.repository.ISongRepository
import com.dreamsoftware.melodiqtv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class VerifySongInFavoritesUseCase(
    private val userRepository: IUserRepository,
    private val profileRepository: IProfilesRepository,
    private val songRepository: ISongRepository
): FudgeTvUseCaseWithParams<VerifySongInFavoritesUseCase.Params, Boolean>() {

    override suspend fun onExecuted(params: Params) = with(params) {
        val userUid = userRepository.getAuthenticatedUid()
        val profileSelected = profileRepository.getProfileSelectedByUser(userUid)
        songRepository.hasSongInFavorites(
            profileId = profileSelected.uuid,
            songId = songId
        )
    }

    data class Params(
        val songId: String
    )
}