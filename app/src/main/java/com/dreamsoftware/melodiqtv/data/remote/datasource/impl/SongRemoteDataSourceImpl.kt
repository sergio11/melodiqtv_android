package com.dreamsoftware.melodiqtv.data.remote.datasource.impl

import com.dreamsoftware.melodiqtv.data.remote.datasource.ISongRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.melodiqtv.data.remote.dto.request.SongFilterDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.response.SongDTO
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchFeaturedSongsRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchRecommendedSongsRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchSongByCategoryRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchSongByIdRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchSongsRemoteException
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.CoroutineDispatcher

internal class SongRemoteDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val dataMapper: IOneSideMapper<Map<String, Any?>, SongDTO>,
    dispatcher: CoroutineDispatcher
) : SupportFireStoreDataSourceImpl(dispatcher), ISongRemoteDataSource {


    private companion object {
        const val COLLECTION_NAME = "melodiq_songs"
        const val CATEGORY_FIELD = "category"
        const val ID_FIELD = "uid"
        const val IS_TRENDING_FIELD = "isTrending"
        const val IS_RECOMMENDED_FIELD = "isRecommended"
        const val LANGUAGE = "language"
        const val DURATION = "duration"
        const val GENRE = "genre"
        const val RELEASED_DATE = "releasedDate"
        const val TYPE = "type"
        const val ARTIST = "artist"
        const val IS_PREMIUM = "isPremium"
    }

    @Throws(FetchSongsRemoteException::class)
    override suspend fun getSongs(filter: SongFilterDTO, includePremium: Boolean): List<SongDTO> = try {
        fetchListFromFireStore(
            query = {
                with(filter) {
                    var query: Query = firebaseStore.collection(COLLECTION_NAME)
                    language?.let { query = query.whereEqualTo(LANGUAGE, it) }
                    genre?.let { query = query.whereEqualTo(GENRE, it) }
                    type?.let { query = query.whereEqualTo(TYPE, it) }
                    artist?.let { query = query.whereEqualTo(ARTIST, it) }
                    videoLength?.let {
                        query = query.whereGreaterThanOrEqualTo(DURATION, it.first.toString())
                            .whereLessThanOrEqualTo(DURATION, it.last.toString())
                    }
                    // Apply sorting
                    if (priorityFeatured) {
                        query = query.orderBy(IS_TRENDING_FIELD, Query.Direction.DESCENDING)
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
            "An error occurred when trying to fetch songs",
            ex
        )
    }

    @Throws(FetchSongByIdRemoteException::class)
    override suspend fun getSongById(id: String): SongDTO = try {
        fetchSingleFromFireStore(
            query = {
                firebaseStore
                    .collection(COLLECTION_NAME)
                    .document(id)
                    .get()
            },
            mapper = { data -> dataMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchSongByIdRemoteException(
            "An error occurred when trying to fetch the songs with ID $id",
            ex
        )
    }

    @Throws(FetchSongByIdRemoteException::class)
    override suspend fun getSongByIdList(idList: List<String>, includePremium: Boolean): List<SongDTO> = try {
        fetchListFromFireStore(
            query = {
                val query = firebaseStore
                    .collection(COLLECTION_NAME)
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
            "An error occurred when trying to fetch songs by ID list",
            ex
        )
    }

    @Throws(FetchSongByCategoryRemoteException::class)
    override suspend fun getSongsByCategory(id: String, includePremium: Boolean): List<SongDTO> = try {
        fetchListFromFireStore(
            query = {
                val query = firebaseStore.collection(COLLECTION_NAME)
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
            "An error occurred when trying to fetch songs by category",
            ex
        )
    }

    @Throws(FetchFeaturedSongsRemoteException::class)
    override suspend fun getFeaturedSongs(includePremium: Boolean): List<SongDTO> = try {
        fetchListFromFireStore(
            query = {
                val query = firebaseStore.collection(COLLECTION_NAME)
                    .whereEqualTo(IS_TRENDING_FIELD, true)
                if(!includePremium) {
                    query.whereEqualTo(IS_PREMIUM, false)
                }
                query.get()
            },
            mapper = { data -> dataMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchFeaturedSongsRemoteException(
            "An error occurred when trying to fetch featured songs",
            ex
        )
    }

    @Throws(FetchRecommendedSongsRemoteException::class)
    override suspend fun getRecommendedSongs(includePremium: Boolean): List<SongDTO> = try {
        fetchListFromFireStore(
            query = {
                val query = firebaseStore.collection(COLLECTION_NAME)
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
            "An error occurred when trying to fetch recommended songs",
            ex
        )
    }
}