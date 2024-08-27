package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.melodiqtv.domain.model.ProfileBO
import com.dreamsoftware.melodiqtv.domain.repository.IProfilesRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class SelectProfileUseCase(
    private val profilesRepository: IProfilesRepository
): FudgeTvUseCaseWithParams<SelectProfileUseCase.Params, Unit>() {

    override suspend fun onExecuted(params: Params): Unit =
        profilesRepository.selectProfile(params.profile)

    data class Params(
        val profile: ProfileBO
    )
}