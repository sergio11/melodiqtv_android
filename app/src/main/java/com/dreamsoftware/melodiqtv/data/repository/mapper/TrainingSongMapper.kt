package com.dreamsoftware.melodiqtv.data.repository.mapper

import com.dreamsoftware.melodiqtv.data.remote.dto.response.TrainingSongDTO
import com.dreamsoftware.melodiqtv.domain.model.TrainingSongBO
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper

internal class TrainingSongMapper : IOneSideMapper<TrainingSongDTO, TrainingSongBO> {

    override fun mapInToOut(input: TrainingSongDTO): TrainingSongBO = with(input) {
        TrainingSongBO(
            id = id,
            title = title,
            description = description,
            imageUrl = imageUrl,
            audioUrl = audioUrl,
            author = author
        )
    }

    override fun mapInListToOutList(input: Iterable<TrainingSongDTO>): Iterable<TrainingSongBO> =
        input.map(::mapInToOut)
}