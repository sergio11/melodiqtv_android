package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.melodiqtv.domain.model.ProfileBO
import com.dreamsoftware.melodiqtv.domain.repository.IProfilesRepository
import com.dreamsoftware.melodiqtv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCase

class GetUserProfilesUseCase(
    private val userRepository: IUserRepository,
    private val profilesRepository: IProfilesRepository
) : FudgeTvUseCase<List<ProfileBO>>() {

    override suspend fun onExecuted(): List<ProfileBO> {
        val userId = userRepository.getAuthenticatedUid()
        return profilesRepository.getProfilesByUser(userId)
    }
}