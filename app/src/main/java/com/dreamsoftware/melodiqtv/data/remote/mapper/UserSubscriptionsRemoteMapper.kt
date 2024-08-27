package com.dreamsoftware.melodiqtv.data.remote.mapper

import com.dreamsoftware.melodiqtv.data.remote.dto.response.UserSubscriptionDTO
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper
import com.google.firebase.Timestamp

internal class UserSubscriptionsRemoteMapper: IOneSideMapper<Map<String, Any?>, UserSubscriptionDTO> {

    private companion object {
        const val UID_KEY = "uid"
        const val SUBSCRIPTION_KEY = "subscriptionId"
        const val USER_KEY = "userId"
        const val START_AT_KEY = "startAt"
        const val VALID_UNTIL_KEY = "validUntil"
    }

    override fun mapInToOut(input: Map<String, Any?>): UserSubscriptionDTO = with(input) {
        UserSubscriptionDTO(
            id = get(UID_KEY) as String,
            subscriptionId = get(SUBSCRIPTION_KEY) as String,
            userId = get(USER_KEY) as String,
            startAt = get(START_AT_KEY) as Timestamp,
            validUntil = get(VALID_UNTIL_KEY) as Timestamp,
        )
    }

    override fun mapInListToOutList(input: Iterable<Map<String, Any?>>): Iterable<UserSubscriptionDTO> =
        input.map(::mapInToOut)
}