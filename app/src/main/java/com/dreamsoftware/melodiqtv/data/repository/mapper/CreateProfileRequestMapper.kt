package com.dreamsoftware.melodiqtv.data.repository.mapper

import com.dreamsoftware.melodiqtv.data.remote.dto.request.CreateProfileRequestDTO
import com.dreamsoftware.melodiqtv.domain.model.CreateProfileRequestBO
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper

internal class CreateProfileRequestMapper :
    IOneSideMapper<CreateProfileRequestBO, CreateProfileRequestDTO> {

    override fun mapInToOut(input: CreateProfileRequestBO): CreateProfileRequestDTO = with(input) {
        CreateProfileRequestDTO(
            uid = uid,
            alias = alias,
            pin = pin,
            avatarType = avatarType.name,
            userId = userId
        )
    }

    override fun mapInListToOutList(input: Iterable<CreateProfileRequestBO>): Iterable<CreateProfileRequestDTO> =
        input.map(::mapInToOut)
}