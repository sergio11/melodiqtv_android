package com.dreamsoftware.melodiqtv.data.repository.mapper

import com.dreamsoftware.melodiqtv.data.remote.dto.response.ArtistDTO
import com.dreamsoftware.melodiqtv.domain.model.ArtistBO
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper

internal class ArtistMapper : IOneSideMapper<ArtistDTO, ArtistBO> {

    override fun mapInToOut(input: ArtistDTO): ArtistBO = with(input) {
        ArtistBO(
            id = id,
            name = name,
            description = description,
            imageUrl = imageUrl
        )
    }

    override fun mapInListToOutList(input: Iterable<ArtistDTO>): Iterable<ArtistBO> =
        input.map(::mapInToOut)
}