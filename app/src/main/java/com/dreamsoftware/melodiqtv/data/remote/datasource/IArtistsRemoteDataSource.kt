package com.dreamsoftware.melodiqtv.data.remote.datasource

import com.dreamsoftware.melodiqtv.data.remote.dto.response.ArtistDTO
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchArtistByIdRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchArtistsRemoteException

interface IArtistsRemoteDataSource {

    @Throws(FetchArtistsRemoteException::class)
    suspend fun getArtists(): List<ArtistDTO>

    @Throws(FetchArtistByIdRemoteException::class)
    suspend fun getArtistById(id: String): ArtistDTO
}