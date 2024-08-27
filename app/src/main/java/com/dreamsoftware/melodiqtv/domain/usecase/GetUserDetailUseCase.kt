package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.melodiqtv.domain.model.UserDetailBO
import com.dreamsoftware.melodiqtv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCase

class GetUserDetailUseCase(
    private val userRepository: IUserRepository
): FudgeTvUseCase<UserDetailBO>() {
    override suspend fun onExecuted(): UserDetailBO =
        userRepository.getDetail()
}