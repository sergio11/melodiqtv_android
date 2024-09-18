package com.dreamsoftware.melodiqtv.data.remote.mapper

import com.dreamsoftware.melodiqtv.data.remote.dto.request.AddFavoriteSongDTO
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper

internal class AddFavoriteSongRemoteMapper:
    IOneSideMapper<AddFavoriteSongDTO, Map<String, Any?>> {

    private companion object {
        const val SONG_ID = "songId"
    }

    override fun mapInToOut(input: AddFavoriteSongDTO): Map<String, Any?> = with(input) {
        hashMapOf(
            SONG_ID to songId,
        )
    }

    override fun mapInListToOutList(input: Iterable<AddFavoriteSongDTO>): Iterable<Map<String, Any?>> =
        input.map(::mapInToOut)
}