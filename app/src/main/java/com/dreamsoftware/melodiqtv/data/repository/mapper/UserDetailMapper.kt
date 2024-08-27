package com.dreamsoftware.melodiqtv.data.repository.mapper

import com.dreamsoftware.melodiqtv.data.remote.dto.response.UserResponseDTO
import com.dreamsoftware.melodiqtv.domain.model.UserDetailBO
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper

internal class UserDetailMapper : IOneSideMapper<UserResponseDTO, UserDetailBO> {

    override fun mapInToOut(input: UserResponseDTO): UserDetailBO = with(input) {
        UserDetailBO(
            uuid = uuid,
            username = username,
            email = email,
            firstName = firstName,
            lastName = lastName,
            profilesCount = profilesCount
        )
    }

    override fun mapInListToOutList(input: Iterable<UserResponseDTO>): Iterable<UserDetailBO> =
        input.map(::mapInToOut)
}