package com.dreamsoftware.melodiqtv.data.remote.datasource

import com.dreamsoftware.melodiqtv.data.remote.dto.response.SubscriptionDTO
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchSubscriptionsRemoteException

interface ISubscriptionsRemoteDataSource {

    @Throws(FetchSubscriptionsRemoteException::class)
    suspend fun getSubscriptions(): List<SubscriptionDTO>
}