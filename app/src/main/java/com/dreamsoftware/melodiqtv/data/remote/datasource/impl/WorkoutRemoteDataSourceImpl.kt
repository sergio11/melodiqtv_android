package com.dreamsoftware.melodiqtv.data.remote.datasource.impl

import com.dreamsoftware.melodiqtv.data.remote.datasource.IWorkoutRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.dto.response.WorkoutDTO
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher

internal class WorkoutRemoteDataSourceImpl(
    firebaseStore: FirebaseFirestore,
    workoutMapper: IOneSideMapper<Map<String, Any?>, WorkoutDTO>,
    dispatcher: CoroutineDispatcher
) : TrainingProgramRemoteDataSourceImpl<WorkoutDTO>(COLLECTION_NAME, firebaseStore, workoutMapper, dispatcher),
    IWorkoutRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "workouts"
    }
}