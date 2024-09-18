package com.dreamsoftware.melodiqtv.domain.repository

import com.dreamsoftware.melodiqtv.domain.exception.AddFavoriteSongException
import com.dreamsoftware.melodiqtv.domain.exception.FetchFavoritesSongsByUserException
import com.dreamsoftware.melodiqtv.domain.exception.FetchFeaturedSongsException
import com.dreamsoftware.melodiqtv.domain.exception.FetchSongByCategoryException
import com.dreamsoftware.melodiqtv.domain.exception.FetchSongByIdException
import com.dreamsoftware.melodiqtv.domain.exception.FetchSongsException
import com.dreamsoftware.melodiqtv.domain.exception.FetchSongsRecommendedException
import com.dreamsoftware.melodiqtv.domain.exception.RemoveFavoriteSongException
import com.dreamsoftware.melodiqtv.domain.exception.VerifyFavoriteSongException
import com.dreamsoftware.melodiqtv.domain.model.AddFavoriteSongBO
import com.dreamsoftware.melodiqtv.domain.model.SongBO
import com.dreamsoftware.melodiqtv.domain.model.SongFilterDataBO

interface ISongRepository {

    @Throws(FetchSongsException::class)
    suspend fun getSongs(data: SongFilterDataBO, includePremium: Boolean = false): Iterable<SongBO>

    @Throws(FetchSongByIdException::class)
    suspend fun getSongById(id: String): SongBO

    @Throws(FetchSongsRecommendedException::class)
    suspend fun getSongsRecommended(includePremium: Boolean = false): Iterable<SongBO>

    @Throws(FetchFeaturedSongsException::class)
    suspend fun getFeaturedSongs(includePremium: Boolean = false): Iterable<SongBO>

    @Throws(FetchSongByCategoryException::class)
    suspend fun getSongsByCategory(id: String, includePremium: Boolean = false): Iterable<SongBO>

    @Throws(AddFavoriteSongException::class)
    suspend fun addFavoriteSong(data: AddFavoriteSongBO): Boolean

    @Throws(FetchFavoritesSongsByUserException::class)
    suspend fun getFavoritesSongsByProfile(profileId: String): List<SongBO>

    @Throws(VerifyFavoriteSongException::class)
    suspend fun hasSongInFavorites(profileId: String, songId: String): Boolean

    @Throws(RemoveFavoriteSongException::class)
    suspend fun removeFavoriteSong(profileId: String, songId: String): Boolean
}