package com.dreamsoftware.melodiqtv.data.remote.datasource.impl

import com.dreamsoftware.melodiqtv.data.remote.datasource.ISeriesRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.dto.response.SeriesDTO
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher

internal class SeriesRemoteDataSourceImpl(
    firebaseStore: FirebaseFirestore,
    seriesMapper: IOneSideMapper<Map<String, Any?>, SeriesDTO>,
    dispatcher: CoroutineDispatcher
) : TrainingProgramRemoteDataSourceImpl<SeriesDTO>(COLLECTION_NAME, firebaseStore, seriesMapper, dispatcher), ISeriesRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "series"
    }
}