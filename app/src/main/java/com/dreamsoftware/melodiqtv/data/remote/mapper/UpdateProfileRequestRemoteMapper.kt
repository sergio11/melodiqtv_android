package com.dreamsoftware.melodiqtv.data.remote.mapper

import com.dreamsoftware.melodiqtv.data.remote.dto.request.UpdatedProfileRequestDTO
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper

internal class UpdateProfileRequestRemoteMapper:
    IOneSideMapper<UpdatedProfileRequestDTO, Map<String, Any?>> {

    private companion object {
        const val ALIAS_KEY = "alias"
        const val PIN_KEY = "pin"
        const val AVATAR_KEY = "avatarType"
    }

    override fun mapInToOut(input: UpdatedProfileRequestDTO): Map<String, Any?> = with(input) {
        mutableMapOf<String, Any?>().apply {
            alias?.let { put(ALIAS_KEY, it) }
            pin?.let { put(PIN_KEY, it) }
            avatarType?.let { put(AVATAR_KEY, it) }
        }
    }

    override fun mapInListToOutList(input: Iterable<UpdatedProfileRequestDTO>): Iterable<Map<String, Any?>> =
        input.map(::mapInToOut)
}