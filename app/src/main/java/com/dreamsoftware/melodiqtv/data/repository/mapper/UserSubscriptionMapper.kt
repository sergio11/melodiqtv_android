package com.dreamsoftware.melodiqtv.data.repository.mapper

import com.dreamsoftware.melodiqtv.data.remote.dto.response.UserSubscriptionDTO
import com.dreamsoftware.melodiqtv.domain.model.UserSubscriptionBO
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper

internal class UserSubscriptionMapper : IOneSideMapper<UserSubscriptionDTO, UserSubscriptionBO> {

    override fun mapInToOut(input: UserSubscriptionDTO): UserSubscriptionBO = with(input) {
        UserSubscriptionBO(
            id = id,
            subscriptionId = subscriptionId,
            userId = userId,
            startAt = startAt.toDate(),
            validUntil = validUntil.toDate()
        )
    }

    override fun mapInListToOutList(input: Iterable<UserSubscriptionDTO>): Iterable<UserSubscriptionBO> =
        input.map(::mapInToOut)
}