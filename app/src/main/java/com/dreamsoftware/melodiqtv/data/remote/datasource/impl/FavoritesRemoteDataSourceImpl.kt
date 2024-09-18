package com.dreamsoftware.melodiqtv.data.remote.datasource.impl

import com.dreamsoftware.melodiqtv.data.remote.datasource.IFavoritesRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.melodiqtv.data.remote.dto.request.AddFavoriteSongDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.response.FavoriteSongDTO
import com.dreamsoftware.melodiqtv.data.remote.exception.AddToFavoritesRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.DeleteProfileRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.GetFavoritesByUserRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.HasTrainingInFavoritesRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.RemoveAllFavoritesRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.RemoveFromFavoritesRemoteException
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

internal class FavoritesRemoteDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val addFavoriteMapper: IOneSideMapper<AddFavoriteSongDTO, Map<String, Any?>>,
    private val favoriteMapper: IOneSideMapper<Map<String, Any?>, FavoriteSongDTO>,
    private val dispatcher: CoroutineDispatcher
): SupportFireStoreDataSourceImpl(dispatcher), IFavoritesRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "melodiqtv_favorite_songs"
        const val SUB_COLLECTION_NAME = "songs"
    }

    @Throws(AddToFavoritesRemoteException::class)
    override suspend fun addFavorite(data: AddFavoriteSongDTO): Boolean = try {
            withContext(dispatcher) {
                firebaseStore.collection(COLLECTION_NAME)
                    .document(data.profileId)
                    .collection(SUB_COLLECTION_NAME)
                    .document(data.songId)
                    .set(addFavoriteMapper.mapInToOut(data), SetOptions.merge())
                    .await()
                true
            }
        } catch (ex: Exception) {
            throw AddToFavoritesRemoteException(
                "An error occurred when trying to add training to favorites",
                ex
            )
        }

    @Throws(GetFavoritesByUserRemoteException::class)
    override suspend fun getFavoritesByUser(profileId: String): List<FavoriteSongDTO> = try {
        fetchListFromFireStore(
            query = { firebaseStore
                .collection(COLLECTION_NAME)
                .document(profileId)
                .collection(SUB_COLLECTION_NAME)
                .get() },
            mapper = { favoriteMapper.mapInToOut(it) }
        )
    } catch (ex: Exception) {
        throw GetFavoritesByUserRemoteException("An error occurred when trying to fetch favorite trainings", ex)
    }

    @Throws(HasTrainingInFavoritesRemoteException::class)
    override suspend fun hasTrainingInFavorites(profileId: String, trainingId: String): Boolean = try {
        withContext(dispatcher) {
            val document = firebaseStore
                .collection(COLLECTION_NAME)
                .document(profileId)
                .collection(SUB_COLLECTION_NAME)
                .document(trainingId)
                .get()
                .await()
            document.exists()
        }
    } catch (ex: Exception) {
        throw HasTrainingInFavoritesRemoteException("An error occurred when trying to check favorites", ex)
    }

    @Throws(RemoveFromFavoritesRemoteException::class)
    override suspend fun removeFavorite(profileId: String, trainingId: String): Boolean = try {
        withContext(dispatcher) {
            firebaseStore
                .collection(COLLECTION_NAME)
                .document(profileId)
                .collection(SUB_COLLECTION_NAME)
                .document(trainingId)
                .delete()
                .await()
            true
        }
    } catch (ex: Exception) {
        throw DeleteProfileRemoteException(
            "An error occurred when trying to remove training $trainingId from favorites",
            ex
        )
    }

    @Throws(RemoveAllFavoritesRemoteException::class)
    override suspend fun removeFavorites(profileId: String): Boolean = try {
        withContext(dispatcher) {
            // Get all documents in the sub-collection (favorites) for the given profile
            val favoriteDocuments = firebaseStore
                .collection(COLLECTION_NAME)
                .document(profileId)
                .collection(SUB_COLLECTION_NAME)
                .get()
                .await()

            if (!favoriteDocuments.isEmpty) {
                // Create a batch to delete all documents at once
                val batch = firebaseStore.batch()
                for (document in favoriteDocuments.documents) {
                    batch.delete(document.reference)
                }
                // Commit the batch
                batch.commit().await()
            }
            true
        }
    } catch (ex: Exception) {
        throw RemoveAllFavoritesRemoteException(
            "An error occurred when trying to remove all favorites for profile $profileId",
            ex
        )
    }

}