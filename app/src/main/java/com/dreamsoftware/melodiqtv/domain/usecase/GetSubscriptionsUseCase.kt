package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.melodiqtv.domain.model.SubscriptionBO
import com.dreamsoftware.melodiqtv.domain.repository.ISubscriptionsRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCase

class GetSubscriptionsUseCase(
    private val subscriptionsRepository: ISubscriptionsRepository
) : FudgeTvUseCase<List<SubscriptionBO>>() {
    override suspend fun onExecuted(): List<SubscriptionBO> =
        subscriptionsRepository.getSubscriptions()
}