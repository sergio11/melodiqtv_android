package com.dreamsoftware.melodiqtv.data.remote.dto.response


data class ProfileDTO(
    val uuid: String,
    val alias: String,
    val isSecured: Boolean,
    val avatarType: String,
    val userId: String
)