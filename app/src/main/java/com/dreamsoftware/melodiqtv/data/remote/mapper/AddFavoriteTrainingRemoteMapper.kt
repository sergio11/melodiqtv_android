package com.dreamsoftware.melodiqtv.data.remote.mapper

import com.dreamsoftware.melodiqtv.data.remote.dto.request.AddFavoriteTrainingDTO
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper

internal class AddFavoriteTrainingRemoteMapper:
    IOneSideMapper<AddFavoriteTrainingDTO, Map<String, Any?>> {

    private companion object {
        const val TRAINING_ID = "trainingId"
        const val TRAINING_TYPE = "trainingType"
    }

    override fun mapInToOut(input: AddFavoriteTrainingDTO): Map<String, Any?> = with(input) {
        hashMapOf(
            TRAINING_ID to trainingId,
            TRAINING_TYPE to trainingType
        )
    }

    override fun mapInListToOutList(input: Iterable<AddFavoriteTrainingDTO>): Iterable<Map<String, Any?>> =
        input.map(::mapInToOut)
}