package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.melodiqtv.domain.repository.ISubscriptionsRepository
import com.dreamsoftware.melodiqtv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCase

class HasActiveSubscriptionUseCase(
    private val userRepository: IUserRepository,
    private val subscriptionsRepository: ISubscriptionsRepository
): FudgeTvUseCase<Boolean>() {
    override suspend fun onExecuted(): Boolean {
        val userUid = userRepository.getAuthenticatedUid()
        return subscriptionsRepository.hasActiveSubscription(userUid)
    }
}