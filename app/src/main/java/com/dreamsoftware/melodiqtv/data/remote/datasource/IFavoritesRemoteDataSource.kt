package com.dreamsoftware.melodiqtv.data.remote.datasource

import com.dreamsoftware.melodiqtv.data.remote.dto.request.AddFavoriteTrainingDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.response.FavoriteTrainingDTO
import com.dreamsoftware.melodiqtv.data.remote.exception.AddToFavoritesRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.GetFavoritesByUserRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.HasTrainingInFavoritesRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.RemoveAllFavoritesRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.RemoveFromFavoritesRemoteException

interface IFavoritesRemoteDataSource {

    @Throws(AddToFavoritesRemoteException::class)
    suspend fun addFavorite(data: AddFavoriteTrainingDTO): Boolean

    @Throws(GetFavoritesByUserRemoteException::class)
    suspend fun getFavoritesByUser(profileId: String): List<FavoriteTrainingDTO>

    @Throws(HasTrainingInFavoritesRemoteException::class)
    suspend fun hasTrainingInFavorites(profileId: String, trainingId: String): Boolean

    @Throws(RemoveFromFavoritesRemoteException::class)
    suspend fun removeFavorite(profileId: String, trainingId: String): Boolean

    @Throws(RemoveAllFavoritesRemoteException::class)
    suspend fun removeFavorites(profileId: String): Boolean
}