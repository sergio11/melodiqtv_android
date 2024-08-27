package com.dreamsoftware.melodiqtv.data.remote.dto.response

data class AuthUserDTO(
    val uid: String,
    val displayName: String? = null,
    val email: String? = null,
    val photoUrl: String? = null
)
