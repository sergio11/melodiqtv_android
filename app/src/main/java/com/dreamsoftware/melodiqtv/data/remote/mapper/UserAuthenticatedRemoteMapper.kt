package com.dreamsoftware.melodiqtv.data.remote.mapper

import com.dreamsoftware.melodiqtv.data.remote.dto.response.AuthUserDTO
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper
import com.google.firebase.auth.FirebaseUser

internal class UserAuthenticatedRemoteMapper : IOneSideMapper<FirebaseUser, AuthUserDTO> {

    override fun mapInToOut(input: FirebaseUser): AuthUserDTO = with(input) {
        AuthUserDTO(
            uid = uid,
            displayName = displayName,
            email = email
        )
    }

    override fun mapInListToOutList(input: Iterable<FirebaseUser>): Iterable<AuthUserDTO> =
        input.map(::mapInToOut)
}