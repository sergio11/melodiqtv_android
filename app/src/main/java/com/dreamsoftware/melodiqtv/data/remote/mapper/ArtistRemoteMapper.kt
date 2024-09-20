package com.dreamsoftware.melodiqtv.data.remote.mapper

import com.dreamsoftware.melodiqtv.data.remote.dto.response.ArtistDTO
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper

internal class ArtistRemoteMapper: IOneSideMapper<Map<String, Any?>, ArtistDTO> {

    private companion object {
        const val UID_KEY = "uid"
        const val NAME_KEY = "name"
        const val DESCRIPTION_KEY = "description"
        const val IMAGE_URL_KEY = "imageUrl"
    }

    override fun mapInToOut(input: Map<String, Any?>): ArtistDTO = with(input) {
        ArtistDTO(
            id = get(UID_KEY) as String,
            name = get(NAME_KEY) as String,
            description = get(DESCRIPTION_KEY) as String,
            imageUrl = get(IMAGE_URL_KEY) as String,
        )
    }

    override fun mapInListToOutList(input: Iterable<Map<String, Any?>>): Iterable<ArtistDTO> =
        input.map(::mapInToOut)
}