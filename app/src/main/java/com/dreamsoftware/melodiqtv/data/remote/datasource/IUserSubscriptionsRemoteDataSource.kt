package com.dreamsoftware.melodiqtv.data.remote.datasource

import com.dreamsoftware.melodiqtv.data.remote.dto.request.AddUserSubscriptionDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.response.UserSubscriptionDTO
import com.dreamsoftware.melodiqtv.data.remote.exception.AddUserSubscriptionRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchUserSubscriptionRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.RemoveUserSubscriptionRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.VerifyHasActiveSubscriptionRemoteException

interface IUserSubscriptionsRemoteDataSource {

    @Throws(FetchUserSubscriptionRemoteException::class)
    suspend fun getUserSubscription(userId: String): UserSubscriptionDTO

    @Throws(VerifyHasActiveSubscriptionRemoteException::class)
    suspend fun hasActiveSubscription(userId: String): Boolean

    @Throws(AddUserSubscriptionRemoteException::class)
    suspend fun addUserSubscription(data: AddUserSubscriptionDTO): Boolean

    @Throws(RemoveUserSubscriptionRemoteException::class)
    suspend fun removeUserSubscription(userId: String): Boolean
}