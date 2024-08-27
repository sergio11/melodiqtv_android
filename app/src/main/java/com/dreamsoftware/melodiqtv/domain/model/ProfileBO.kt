package com.dreamsoftware.melodiqtv.domain.model

data class ProfileBO(
    val uuid: String,
    val alias: String,
    val isSecured: Boolean,
    val avatarType: AvatarTypeEnum
)