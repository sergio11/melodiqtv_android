package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.melodiqtv.domain.model.ProfileBO
import com.dreamsoftware.melodiqtv.domain.repository.IProfilesRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class GetProfileByIdUseCase(
    private val profilesRepository: IProfilesRepository
): FudgeTvUseCaseWithParams<GetProfileByIdUseCase.Params, ProfileBO>() {

    override suspend fun onExecuted(params: Params): ProfileBO =
        profilesRepository.getProfileById(params.profileId)

    data class Params(
        val profileId: String
    )
}