package com.dreamsoftware.melodiqtv.data.remote.datasource

import com.dreamsoftware.melodiqtv.data.remote.dto.response.TrainingSongDTO
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchTrainingSongByIdRemoteException

interface ITrainingSongsRemoteDataSource {

    @Throws(FetchTrainingSongByIdRemoteException::class)
    suspend fun getSongById(songId: String): TrainingSongDTO
}