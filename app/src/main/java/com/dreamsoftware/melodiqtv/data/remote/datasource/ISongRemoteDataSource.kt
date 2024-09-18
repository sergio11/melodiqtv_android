package com.dreamsoftware.melodiqtv.data.remote.datasource

import com.dreamsoftware.melodiqtv.data.remote.dto.request.SongFilterDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.response.SongDTO
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchFeaturedSongsRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchRecommendedSongsRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchSongByCategoryRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchSongByIdRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchSongsRemoteException

interface ISongRemoteDataSource {

    @Throws(FetchSongsRemoteException::class)
    suspend fun getSongs(filter: SongFilterDTO, includePremium: Boolean = false): List<SongDTO>

    @Throws(FetchSongByIdRemoteException::class)
    suspend fun getSongById(id: String): SongDTO

    @Throws(FetchSongByIdRemoteException::class)
    suspend fun getSongByIdList(idList: List<String>, includePremium: Boolean = false): List<SongDTO>

    @Throws(FetchSongByCategoryRemoteException::class)
    suspend fun getSongsByCategory(id: String, includePremium: Boolean = false): List<SongDTO>

    @Throws(FetchFeaturedSongsRemoteException::class)
    suspend fun getFeaturedSongs(includePremium: Boolean = false): List<SongDTO>

    @Throws(FetchRecommendedSongsRemoteException::class)
    suspend fun getRecommendedSongs(includePremium: Boolean = false): List<SongDTO>
}