package com.dreamsoftware.melodiqtv.domain.model

data class AddFavoriteTrainingBO(
    val profileId: String,
    val trainingId: String,
    val trainingType: TrainingTypeEnum
)
