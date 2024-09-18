package com.dreamsoftware.melodiqtv.domain.repository

import com.dreamsoftware.melodiqtv.domain.exception.FetchArtistByIdException
import com.dreamsoftware.melodiqtv.domain.exception.FetchArtistsException
import com.dreamsoftware.melodiqtv.domain.model.ArtistBO

interface IArtistRepository {

    @Throws(FetchArtistsException::class)
    suspend fun getArtists(): List<ArtistBO>

    @Throws(FetchArtistByIdException::class)
    suspend fun getArtistById(id: String): ArtistBO
}