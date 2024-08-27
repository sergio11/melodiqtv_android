package com.dreamsoftware.melodiqtv.data.repository.mapper

import com.dreamsoftware.melodiqtv.data.remote.dto.response.ProfileDTO
import com.dreamsoftware.melodiqtv.domain.model.AvatarTypeEnum
import com.dreamsoftware.melodiqtv.domain.model.ProfileBO
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper
import com.dreamsoftware.melodiqtv.utils.enumNameOfOrDefault

internal class ProfileMapper : IOneSideMapper<ProfileDTO, ProfileBO> {

    override fun mapInToOut(input: ProfileDTO): ProfileBO = with(input) {
        ProfileBO(
            uuid = uuid,
            alias = alias,
            isSecured = isSecured,
            avatarType = enumNameOfOrDefault(avatarType, AvatarTypeEnum.BOY)
        )
    }

    override fun mapInListToOutList(input: Iterable<ProfileDTO>): Iterable<ProfileBO> =
        input.map(::mapInToOut)
}