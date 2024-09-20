package com.dreamsoftware.melodiqtv.data.remote.mapper

import com.dreamsoftware.melodiqtv.data.remote.dto.response.FavoriteSongDTO
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper

internal class FavoriteSongRemoteMapper:
    IOneSideMapper<Map<String, Any?>, FavoriteSongDTO> {

    private companion object {
        const val SONG_ID = "songId"
    }

    override fun mapInToOut(input: Map<String, Any?>): FavoriteSongDTO = with(input) {
        FavoriteSongDTO(
            songId = get(SONG_ID) as String,
        )
    }

    override fun mapInListToOutList(input: Iterable<Map<String, Any?>>): Iterable<FavoriteSongDTO> =
        input.map(::mapInToOut)
}