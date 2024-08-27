package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.melodiqtv.domain.model.AddUserSubscriptionBO
import com.dreamsoftware.melodiqtv.domain.repository.ISubscriptionsRepository
import com.dreamsoftware.melodiqtv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams
import java.util.Calendar
import java.util.UUID

class AddUserSubscriptionUseCase(
    private val userRepository: IUserRepository,
    private val subscriptionsRepository: ISubscriptionsRepository
) : FudgeTvUseCaseWithParams<AddUserSubscriptionUseCase.Params, Boolean>() {

    override suspend fun onExecuted(params: Params): Boolean = with(params) {
        val userId = userRepository.getAuthenticatedUid()
        val currentDate = System.currentTimeMillis()
        val calendar = Calendar.getInstance().apply {
            timeInMillis = currentDate
            add(Calendar.MONTH, months)
        }
        subscriptionsRepository.addUserSubscription(AddUserSubscriptionBO(
            id = UUID.randomUUID().toString(),
            subscriptionId = subscriptionId,
            userId = userId,
            validUntil = calendar.timeInMillis
        ))
    }

    data class Params(
        val subscriptionId: String,
        val months: Int
    )
}