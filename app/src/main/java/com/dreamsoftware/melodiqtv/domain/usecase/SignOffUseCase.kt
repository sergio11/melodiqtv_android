package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.melodiqtv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCase

class SignOffUseCase(
    private val userRepository: IUserRepository,
): FudgeTvUseCase<Unit>() {
    override suspend fun onExecuted() {
        userRepository.signOff()
    }
}