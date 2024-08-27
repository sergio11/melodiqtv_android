package com.dreamsoftware.melodiqtv.data.remote.dto.request

data class AddUserSubscriptionDTO(
    val id: String,
    val subscriptionId: String,
    val userId: String,
    val validUntil: Long
)
