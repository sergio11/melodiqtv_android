package com.dreamsoftware.melodiqtv.domain.repository

import com.dreamsoftware.melodiqtv.domain.exception.FetchTrainingSongsByIdException
import com.dreamsoftware.melodiqtv.domain.model.TrainingSongBO

interface ITrainingSongsRepository {

    @Throws(FetchTrainingSongsByIdException::class)
    suspend fun getSongById(id: String): TrainingSongBO
}