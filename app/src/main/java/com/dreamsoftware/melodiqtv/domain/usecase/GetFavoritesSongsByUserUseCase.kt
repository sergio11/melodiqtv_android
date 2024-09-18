package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.fudge.core.FudgeTvUseCase
import com.dreamsoftware.melodiqtv.domain.model.SongBO
import com.dreamsoftware.melodiqtv.domain.repository.IProfilesRepository
import com.dreamsoftware.melodiqtv.domain.repository.ISongRepository
import com.dreamsoftware.melodiqtv.domain.repository.IUserRepository

class GetFavoritesSongsByUserUseCase(
    private val userRepository: IUserRepository,
    private val profileRepository: IProfilesRepository,
    private val songRepository: ISongRepository
): FudgeTvUseCase<List<SongBO>>() {

    override suspend fun onExecuted(): List<SongBO> {
        val userUid = userRepository.getAuthenticatedUid()
        val profileSelected = profileRepository.getProfileSelectedByUser(userUid)
        return songRepository.getFavoritesSongsByProfile(profileSelected.uuid)
    }
}