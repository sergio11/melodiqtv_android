package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.melodiqtv.domain.model.AddFavoriteSongBO
import com.dreamsoftware.melodiqtv.domain.repository.IProfilesRepository
import com.dreamsoftware.melodiqtv.domain.repository.ISongRepository
import com.dreamsoftware.melodiqtv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class AddFavoriteSongUseCase(
    private val userRepository: IUserRepository,
    private val profileRepository: IProfilesRepository,
    private val songRepository: ISongRepository
) : FudgeTvUseCaseWithParams<AddFavoriteSongUseCase.Params, Boolean>() {

    override suspend fun onExecuted(params: Params) = with(params) {
        val currentUserUid = userRepository.getAuthenticatedUid()
        val profileSelected = profileRepository.getProfileSelectedByUser(currentUserUid)
        toAddFavoriteSongBO(profileSelected.uuid)
            .let { songRepository.addFavoriteSong(it) }
    }

    private fun Params.toAddFavoriteSongBO(profileId: String) = AddFavoriteSongBO(
        profileId = profileId,
        songId = songId
    )

    data class Params(
        val songId: String
    )
}