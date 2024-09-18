package com.dreamsoftware.melodiqtv.data.repository.impl

import com.dreamsoftware.melodiqtv.data.remote.datasource.IArtistsRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.dto.response.ArtistDTO
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchArtistByIdRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchArtistsRemoteException
import com.dreamsoftware.melodiqtv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.melodiqtv.domain.exception.FetchArtistByIdException
import com.dreamsoftware.melodiqtv.domain.exception.FetchArtistsException
import com.dreamsoftware.melodiqtv.domain.model.ArtistBO
import com.dreamsoftware.melodiqtv.domain.repository.IArtistRepository
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper
import kotlinx.coroutines.CoroutineDispatcher

internal class ArtistRepositoryImpl(
    private val artistsRemoteDataSource: IArtistsRemoteDataSource,
    private val artistsMapper: IOneSideMapper<ArtistDTO, ArtistBO>,
    dispatcher: CoroutineDispatcher
) : SupportRepositoryImpl(dispatcher), IArtistRepository {

    @Throws(FetchArtistsException::class)
    override suspend fun getArtists(): List<ArtistBO> = safeExecute {
        try {
            artistsRemoteDataSource
                .getArtists()
                .let(artistsMapper::mapInListToOutList)
                .toList()
        } catch (ex: FetchArtistsRemoteException) {
            throw FetchArtistsException("An error occurred when fetching artists", ex)
        }
    }

    @Throws(FetchArtistByIdException::class)
    override suspend fun getArtistById(id: String): ArtistBO = safeExecute {
        try {
            artistsRemoteDataSource
                .getArtistById(id)
                .let(artistsMapper::mapInToOut)
        } catch (ex: FetchArtistByIdRemoteException) {
            throw FetchArtistByIdException("An error occurred when fetching the artist by id: $id", ex)
        }
    }
}