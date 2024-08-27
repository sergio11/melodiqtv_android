package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.melodiqtv.domain.model.UserPreferenceBO
import com.dreamsoftware.melodiqtv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCase

class GetUserPreferencesUseCase(
    private val userRepository: IUserRepository,
) : FudgeTvUseCase<UserPreferenceBO>() {

    override suspend fun onExecuted(): UserPreferenceBO =
        userRepository.getUserPreferences()
}