package com.dreamsoftware.melodiqtv.data.remote.dto.request

data class AddFavoriteTrainingDTO(
    val profileId: String,
    val trainingId: String,
    val trainingType: String
)
