package com.dreamsoftware.melodiqtv.data.remote.datasource.impl

import com.dreamsoftware.melodiqtv.data.remote.datasource.IArtistsRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.melodiqtv.data.remote.dto.response.ArtistDTO
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchArtistByIdRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchArtistsRemoteException
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher

internal class ArtistsRemoteDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val artistsMapper: IOneSideMapper<Map<String, Any?>, ArtistDTO>,
    dispatcher: CoroutineDispatcher
) : SupportFireStoreDataSourceImpl(dispatcher), IArtistsRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "melodiqtv_artists"
    }

    @Throws(FetchArtistsRemoteException::class)
    override suspend fun getArtists(): List<ArtistDTO> = try {
        fetchListFromFireStore(
            query = { firebaseStore.collection(COLLECTION_NAME).get() },
            mapper = { artistsMapper.mapInToOut(it) }
        )
    } catch (ex: Exception) {
        throw FetchArtistsRemoteException(
            "An error occurred when trying to fetch artists",
            ex
        )
    }

    @Throws(FetchArtistByIdRemoteException::class)
    override suspend fun getArtistById(id: String): ArtistDTO = try {
        fetchSingleFromFireStore(
            query = {
                firebaseStore.collection(COLLECTION_NAME)
                    .document(id)
                    .get()
            },
            mapper = { artistsMapper.mapInToOut(it) }
        )
    } catch (ex: Exception) {
        throw FetchArtistByIdRemoteException(
            "An error occurred when trying to fetch artists",
            ex
        )
    }
}