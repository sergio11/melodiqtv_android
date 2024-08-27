package com.dreamsoftware.melodiqtv.data.repository.impl

import com.dreamsoftware.melodiqtv.data.remote.datasource.ISubscriptionsRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.datasource.IUserSubscriptionsRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.dto.request.AddUserSubscriptionDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.response.SubscriptionDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.response.UserSubscriptionDTO
import com.dreamsoftware.melodiqtv.data.remote.exception.AddUserSubscriptionRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchSubscriptionsRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.RemoveUserSubscriptionRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.VerifyHasActiveSubscriptionRemoteException
import com.dreamsoftware.melodiqtv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.melodiqtv.domain.exception.AddUserSubscriptionException
import com.dreamsoftware.melodiqtv.domain.exception.FetchSubscriptionsException
import com.dreamsoftware.melodiqtv.domain.exception.FetchUserSubscriptionException
import com.dreamsoftware.melodiqtv.domain.exception.RemoveUserSubscriptionException
import com.dreamsoftware.melodiqtv.domain.exception.VerifyHasActiveSubscriptionException
import com.dreamsoftware.melodiqtv.domain.model.AddUserSubscriptionBO
import com.dreamsoftware.melodiqtv.domain.model.SubscriptionBO
import com.dreamsoftware.melodiqtv.domain.model.UserSubscriptionBO
import com.dreamsoftware.melodiqtv.domain.repository.ISubscriptionsRepository
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper
import kotlinx.coroutines.CoroutineDispatcher

internal class SubscriptionsRepositoryImpl(
    private val userSubscriptionRemoteDataSource: IUserSubscriptionsRemoteDataSource,
    private val subscriptionsRemoteDataSource: ISubscriptionsRemoteDataSource,
    private val subscriptionMapper: IOneSideMapper<SubscriptionDTO, SubscriptionBO>,
    private val addUserSubscriptionMapper: IOneSideMapper<AddUserSubscriptionBO, AddUserSubscriptionDTO>,
    private val userSubscriptionMapper: IOneSideMapper<UserSubscriptionDTO, UserSubscriptionBO>,
    dispatcher: CoroutineDispatcher
): SupportRepositoryImpl(dispatcher), ISubscriptionsRepository {

    @Throws(FetchSubscriptionsException::class)
    override suspend fun getSubscriptions(): List<SubscriptionBO> = safeExecute {
        try {
            subscriptionsRemoteDataSource
                .getSubscriptions()
                .let(subscriptionMapper::mapInListToOutList)
                .toList()
        } catch (ex: FetchSubscriptionsRemoteException) {
            throw FetchSubscriptionsException("An error occurred when fetching subscriptions", ex)
        }
    }

    @Throws(FetchUserSubscriptionException::class)
    override suspend fun getUserSubscription(userId: String): UserSubscriptionBO = safeExecute {
        try {
            userSubscriptionRemoteDataSource
                .getUserSubscription(userId)
                .let(userSubscriptionMapper::mapInToOut)
        } catch (ex: FetchSubscriptionsRemoteException) {
            throw FetchUserSubscriptionException("An error occurred when fetching user subscription", ex)
        }
    }

    @Throws(VerifyHasActiveSubscriptionException::class)
    override suspend fun hasActiveSubscription(userId: String): Boolean = safeExecute {
        try {
            userSubscriptionRemoteDataSource.hasActiveSubscription(userId)
        } catch (ex: VerifyHasActiveSubscriptionRemoteException) {
            throw VerifyHasActiveSubscriptionException("An error occurred when checking if user has active subscription", ex)
        }
    }

    @Throws(AddUserSubscriptionException::class)
    override suspend fun addUserSubscription(data: AddUserSubscriptionBO): Boolean = safeExecute {
        try {
            userSubscriptionRemoteDataSource.addUserSubscription(addUserSubscriptionMapper.mapInToOut(data))
        } catch (ex: AddUserSubscriptionRemoteException) {
            throw AddUserSubscriptionException("An error occurred when adding user subscription", ex)
        }
    }

    @Throws(RemoveUserSubscriptionException::class)
    override suspend fun removeUserSubscription(userId: String): Boolean = safeExecute {
        try {
            userSubscriptionRemoteDataSource.removeUserSubscription(userId)
        } catch (ex: RemoveUserSubscriptionRemoteException) {
            throw RemoveUserSubscriptionException("An error occurred when removing user subscription", ex)
        }
    }
}