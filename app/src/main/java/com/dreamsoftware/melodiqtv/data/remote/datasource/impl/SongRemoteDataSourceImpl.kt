package com.dreamsoftware.melodiqtv.data.remote.datasource.impl

import com.dreamsoftware.melodiqtv.data.remote.datasource.ISongRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.melodiqtv.data.remote.dto.request.SongFilterDTO
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchFeaturedSongsRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchRecommendedSongsRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchSongByCategoryRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchSongByIdRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchSongsRemoteException
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.CoroutineDispatcher

internal abstract class SongRemoteDataSourceImpl<out T>(
    private val collectionName: String,
    private val firebaseStore: FirebaseFirestore,
    private val dataMapper: IOneSideMapper<Map<String, Any?>, T>,
    dispatcher: CoroutineDispatcher
) : SupportFireStoreDataSourceImpl(dispatcher), ISongRemoteDataSource<T> {

    private companion object {
        const val CATEGORY_FIELD = "category"
        const val ID_FIELD = "uid"
        const val IS_FEATURED_FIELD = "isFeatured"
        const val IS_RECOMMENDED_FIELD = "isRecommended"
        const val LANGUAGE = "language"
        const val DURATION = "duration"
        const val INTENSITY = "intensity"
        const val RELEASED_DATE = "releasedDate"
        const val WORKOUT_TYPE = "workoutType"
        const val INSTRUCTOR = "instructor"
        const val IS_PREMIUM = "isPremium"
    }

    @Throws(FetchSongsRemoteException::class)
    override suspend fun getTrainings(filter: SongFilterDTO, includePremium: Boolean): List<T> = try {
        fetchListFromFireStore(
            query = {
                with(filter) {
                    var query: Query = firebaseStore.collection(collectionName)
                    classLanguage?.let { query = query.whereEqualTo(LANGUAGE, it) }
                    intensity?.let { query = query.whereEqualTo(INTENSITY, it) }
                    workoutType?.let { query = query.whereEqualTo(WORKOUT_TYPE, it) }
                    instructor?.let { query = query.whereEqualTo(INSTRUCTOR, it) }
                    videoLength?.let {
                        query = query.whereGreaterThanOrEqualTo(DURATION, it.first.toString())
                            .whereLessThanOrEqualTo(DURATION, it.last.toString())
                    }
                    // Apply sorting
                    if (priorityFeatured) {
                        query = query.orderBy(IS_FEATURED_FIELD, Query.Direction.DESCENDING)
                    }
                    query = if (orderByReleasedDateDesc) {
                        query.orderBy(RELEASED_DATE, Query.Direction.DESCENDING)
                    } else {
                        query.orderBy(RELEASED_DATE, Query.Direction.ASCENDING)
                    }
                    if(!includePremium) {
                        query.whereEqualTo(IS_PREMIUM, false)
                    }
                    query.get()
                }
            },
            mapper = { data -> dataMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchSongsRemoteException(
            "An error occurred when trying to fetch trainings",
            ex
        )
    }

    @Throws(FetchSongByIdRemoteException::class)
    override suspend fun getTrainingById(id: String): T = try {
        fetchSingleFromFireStore(
            query = {
                firebaseStore
                    .collection(collectionName)
                    .document(id)
                    .get()
            },
            mapper = { data -> dataMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchSongByIdRemoteException(
            "An error occurred when trying to fetch the training with ID $id",
            ex
        )
    }

    @Throws(FetchSongByIdRemoteException::class)
    override suspend fun getTrainingByIdList(idList: List<String>, includePremium: Boolean): List<T> = try {
        fetchListFromFireStore(
            query = {
                val query = firebaseStore
                    .collection(collectionName)
                    .whereIn(ID_FIELD, idList)
                if(!includePremium) {
                    query.whereEqualTo(IS_PREMIUM, false)
                }
                query.get()
            },
            mapper = { data -> dataMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchSongByIdRemoteException(
            "An error occurred when trying to fetch trainings by ID list",
            ex
        )
    }

    @Throws(FetchSongByCategoryRemoteException::class)
    override suspend fun getTrainingByCategory(id: String, includePremium: Boolean): List<T> = try {
        fetchListFromFireStore(
            query = {
                val query = firebaseStore.collection(collectionName)
                    .whereEqualTo(CATEGORY_FIELD, id)
                if(!includePremium) {
                    query.whereEqualTo(IS_PREMIUM, false)
                }
                query.get()
            },
            mapper = { data -> dataMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchSongByCategoryRemoteException(
            "An error occurred when trying to fetch trainings by category",
            ex
        )
    }

    @Throws(FetchFeaturedSongsRemoteException::class)
    override suspend fun getFeaturedTrainings(includePremium: Boolean): List<T> = try {
        fetchListFromFireStore(
            query = {
                val query = firebaseStore.collection(collectionName)
                    .whereEqualTo(IS_FEATURED_FIELD, true)
                if(!includePremium) {
                    query.whereEqualTo(IS_PREMIUM, false)
                }
                query.get()
            },
            mapper = { data -> dataMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchFeaturedSongsRemoteException(
            "An error occurred when trying to fetch featured trainings",
            ex
        )
    }

    @Throws(FetchRecommendedSongsRemoteException::class)
    override suspend fun getRecommendedTrainings(includePremium: Boolean): List<T> = try {
        fetchListFromFireStore(
            query = {
                val query = firebaseStore.collection(collectionName)
                    .whereEqualTo(IS_RECOMMENDED_FIELD, true)
                if(!includePremium) {
                    query.whereEqualTo(IS_PREMIUM, false)
                }
                query.get()
            },
            mapper = { data -> dataMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchRecommendedSongsRemoteException(
            "An error occurred when trying to fetch recommended trainings",
            ex
        )
    }
}