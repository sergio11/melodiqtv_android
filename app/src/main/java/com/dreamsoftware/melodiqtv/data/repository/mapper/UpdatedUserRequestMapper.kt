package com.dreamsoftware.melodiqtv.data.repository.mapper

import com.dreamsoftware.melodiqtv.data.remote.dto.request.UpdatedUserRequestDTO
import com.dreamsoftware.melodiqtv.domain.model.UpdatedUserRequestBO
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper

internal class UpdatedUserRequestMapper :
    IOneSideMapper<UpdatedUserRequestBO, UpdatedUserRequestDTO> {

    override fun mapInToOut(input: UpdatedUserRequestBO): UpdatedUserRequestDTO = with(input) {
        UpdatedUserRequestDTO(
            firstName = firstName,
            lastName = lastName,
            username = username
        )
    }

    override fun mapInListToOutList(input: Iterable<UpdatedUserRequestBO>): Iterable<UpdatedUserRequestDTO> =
        input.map(::mapInToOut)
}