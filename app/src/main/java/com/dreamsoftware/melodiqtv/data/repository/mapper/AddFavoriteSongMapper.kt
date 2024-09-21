package com.dreamsoftware.melodiqtv.data.repository.mapper

import com.dreamsoftware.melodiqtv.data.remote.dto.request.AddFavoriteSongDTO
import com.dreamsoftware.melodiqtv.domain.model.AddFavoriteSongBO
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper

internal class AddFavoriteSongMapper :
    IOneSideMapper<AddFavoriteSongBO, AddFavoriteSongDTO> {

    override fun mapInToOut(input: AddFavoriteSongBO): AddFavoriteSongDTO = with(input) {
        AddFavoriteSongDTO(
            profileId = profileId,
            songId = songId
        )
    }

    override fun mapInListToOutList(input: Iterable<AddFavoriteSongBO>): Iterable<AddFavoriteSongDTO> =
        input.map(::mapInToOut)
}