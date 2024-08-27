package com.dreamsoftware.melodiqtv.data.repository.mapper

import com.dreamsoftware.melodiqtv.data.remote.dto.request.CreateUserDTO
import com.dreamsoftware.melodiqtv.domain.model.SignUpBO
import com.dreamsoftware.melodiqtv.ui.utils.EMPTY
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper

internal class CreateUserMapper : IOneSideMapper<SignUpBO, CreateUserDTO> {

    override fun mapInToOut(input: SignUpBO): CreateUserDTO = with(input) {
        CreateUserDTO(
            uid = String.EMPTY,
            email = email,
            username = username,
            firstName = firstName,
            lastName = lastName
        )
    }

    override fun mapInListToOutList(input: Iterable<SignUpBO>): Iterable<CreateUserDTO> =
        input.map(::mapInToOut)
}