package com.dreamsoftware.melodiqtv.data.remote.datasource

import com.dreamsoftware.melodiqtv.data.remote.dto.request.TrainingFilterDTO
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchFeaturedTrainingsRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchRecommendedTrainingsRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchTrainingByCategoryRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchTrainingByIdRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchTrainingsRemoteException

interface ITrainingProgramRemoteDataSource<out T> {

    @Throws(FetchTrainingsRemoteException::class)
    suspend fun getTrainings(filter: TrainingFilterDTO, includePremium: Boolean = false): List<T>

    @Throws(FetchTrainingByIdRemoteException::class)
    suspend fun getTrainingById(id: String): T

    @Throws(FetchTrainingByIdRemoteException::class)
    suspend fun getTrainingByIdList(idList: List<String>, includePremium: Boolean = false): List<T>

    @Throws(FetchTrainingByCategoryRemoteException::class)
    suspend fun getTrainingByCategory(id: String, includePremium: Boolean = false): List<T>

    @Throws(FetchFeaturedTrainingsRemoteException::class)
    suspend fun getFeaturedTrainings(includePremium: Boolean = false): List<T>

    @Throws(FetchRecommendedTrainingsRemoteException::class)
    suspend fun getRecommendedTrainings(includePremium: Boolean = false): List<T>
}