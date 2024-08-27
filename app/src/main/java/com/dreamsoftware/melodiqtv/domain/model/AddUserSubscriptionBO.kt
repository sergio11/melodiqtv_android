package com.dreamsoftware.melodiqtv.domain.model

data class AddUserSubscriptionBO(
    val id: String,
    val subscriptionId: String,
    val userId: String,
    val validUntil: Long
)
