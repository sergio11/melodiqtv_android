package com.dreamsoftware.melodiqtv.data.repository.mapper

import com.dreamsoftware.melodiqtv.data.remote.dto.response.SubscriptionDTO
import com.dreamsoftware.melodiqtv.domain.model.SubscriptionBO
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper

internal class SubscriptionMapper : IOneSideMapper<SubscriptionDTO, SubscriptionBO> {

    override fun mapInToOut(input: SubscriptionDTO): SubscriptionBO = with(input) {
        SubscriptionBO(
            id = id,
            periodTime = periodTime,
            price = price
        )
    }

    override fun mapInListToOutList(input: Iterable<SubscriptionDTO>): Iterable<SubscriptionBO> =
        input.map(::mapInToOut)
}