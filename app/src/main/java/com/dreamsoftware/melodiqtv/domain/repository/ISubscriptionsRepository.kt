package com.dreamsoftware.melodiqtv.domain.repository

import com.dreamsoftware.melodiqtv.domain.exception.AddUserSubscriptionException
import com.dreamsoftware.melodiqtv.domain.exception.FetchSubscriptionsException
import com.dreamsoftware.melodiqtv.domain.exception.FetchUserSubscriptionException
import com.dreamsoftware.melodiqtv.domain.exception.RemoveUserSubscriptionException
import com.dreamsoftware.melodiqtv.domain.exception.VerifyHasActiveSubscriptionException
import com.dreamsoftware.melodiqtv.domain.model.AddUserSubscriptionBO
import com.dreamsoftware.melodiqtv.domain.model.SubscriptionBO
import com.dreamsoftware.melodiqtv.domain.model.UserSubscriptionBO

interface ISubscriptionsRepository {

    @Throws(FetchSubscriptionsException::class)
    suspend fun getSubscriptions(): List<SubscriptionBO>

    @Throws(FetchUserSubscriptionException::class)
    suspend fun getUserSubscription(userId: String): UserSubscriptionBO

    @Throws(VerifyHasActiveSubscriptionException::class)
    suspend fun hasActiveSubscription(userId: String): Boolean

    @Throws(AddUserSubscriptionException::class)
    suspend fun addUserSubscription(data: AddUserSubscriptionBO): Boolean

    @Throws(RemoveUserSubscriptionException::class)
    suspend fun removeUserSubscription(userId: String): Boolean
}