package com.dreamsoftware.melodiqtv.data.repository.impl

import com.dreamsoftware.melodiqtv.data.remote.datasource.IArtistsRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.datasource.ICategoryRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.datasource.IFavoritesRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.datasource.ISongRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.dto.request.AddFavoriteSongDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.request.SongFilterDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.response.SongDTO
import com.dreamsoftware.melodiqtv.data.remote.exception.AddToFavoritesRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.GetFavoritesByUserRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.HasSongInFavoritesRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.RemoteDataSourceException
import com.dreamsoftware.melodiqtv.data.remote.exception.RemoveFromFavoritesRemoteException
import com.dreamsoftware.melodiqtv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.melodiqtv.data.repository.mapper.SongMapperData
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
import com.dreamsoftware.melodiqtv.domain.repository.ISongRepository
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper
import com.dreamsoftware.melodiqtv.utils.parallelMap
import kotlinx.coroutines.CoroutineDispatcher

internal class SongRepositoryImpl(
    private val songRemoteDataSource: ISongRemoteDataSource,
    private val favoritesRemoteDataSource: IFavoritesRemoteDataSource,
    private val artistRemoteDataSource: IArtistsRemoteDataSource,
    private val categoryRemoteDataSource: ICategoryRemoteDataSource,
    private val songsMapper: IOneSideMapper<SongMapperData, SongBO>,
    private val addFavoriteMapper: IOneSideMapper<AddFavoriteSongBO, AddFavoriteSongDTO>,
    private val filterDataMapper: IOneSideMapper<SongFilterDataBO, SongFilterDTO>,
    dispatcher: CoroutineDispatcher
) : SupportRepositoryImpl(dispatcher), ISongRepository {

    @Throws(FetchSongsException::class)
    override suspend fun getSongs(data: SongFilterDataBO, includePremium: Boolean): Iterable<SongBO> =
        safeExecute {
            try {
                val filterDTO = filterDataMapper.mapInToOut(data)
                songRemoteDataSource.getSongs(filterDTO, includePremium).mapToSongBoList()
            } catch (ex: RemoteDataSourceException) {
                throw FetchSongsException("An error occurred when fetching songs", ex)
            }
        }

    @Throws(FetchSongByIdException::class)
    override suspend fun getSongById(id: String): SongBO =
        safeExecute {
            try {
                songRemoteDataSource.getSongById(id).mapToSongBo()
            } catch (ex: RemoteDataSourceException) {
                throw FetchSongsException("An error occurred when fetching the song", ex)
            }
        }

    @Throws(FetchSongsRecommendedException::class)
    override suspend fun getSongsRecommended(includePremium: Boolean): Iterable<SongBO> = safeExecute {
        try {
            songRemoteDataSource.getRecommendedSongs(includePremium).mapToSongBoList()
        } catch (ex: RemoteDataSourceException) {
            throw FetchSongsRecommendedException(
                "An error occurred when fetching the recommended songs",
                ex
            )
        }
    }

    @Throws(FetchFeaturedSongsException::class)
    override suspend fun getFeaturedSongs(includePremium: Boolean): Iterable<SongBO> = safeExecute {
        try {
            songRemoteDataSource.getFeaturedSongs(includePremium).mapToSongBoList()
        } catch (ex: RemoteDataSourceException) {
            throw FetchFeaturedSongsException(
                "An error occurred when fetching the featured songs",
                ex
            )
        }
    }

    @Throws(FetchSongByCategoryException::class)
    override suspend fun getSongsByCategory(id: String, includePremium: Boolean): Iterable<SongBO> =
        safeExecute {
            try {
                songRemoteDataSource.getSongsByCategory(id, includePremium).mapToSongBoList()
            } catch (ex: RemoteDataSourceException) {
                throw FetchSongByCategoryException(
                    "An error occurred when fetching the song",
                    ex
                )
            }
        }

    @Throws(AddFavoriteSongException::class)
    override suspend fun addFavoriteSong(data: AddFavoriteSongBO): Boolean =
        safeExecute {
            try {
                favoritesRemoteDataSource.addFavorite(addFavoriteMapper.mapInToOut(data))
            } catch (ex: AddToFavoritesRemoteException) {
                throw AddFavoriteSongException(
                    "An error occurred when adding song to favorites",
                    ex
                )
            }
        }

    @Throws(FetchFavoritesSongsByUserException::class)
    override suspend fun getFavoritesSongsByProfile(profileId: String): List<SongBO> = safeExecute {
        try {
            favoritesRemoteDataSource.getFavoritesByUser(profileId).parallelMap {
                getSongById(id = it.songId)
            }
        } catch (ex: GetFavoritesByUserRemoteException) {
            throw FetchFavoritesSongsByUserException(
                "An error occurred when fetching favorites",
                ex
            )
        }
    }

    @Throws(VerifyFavoriteSongException::class)
    override suspend fun hasSongInFavorites(profileId: String, songId: String): Boolean = safeExecute {
        try {
            favoritesRemoteDataSource.hasSongInFavorites(
                profileId = profileId,
                songId = songId
            )
        } catch (ex: HasSongInFavoritesRemoteException) {
            throw VerifyFavoriteSongException(
                "An error occurred when checking favorites",
                ex
            )
        }
    }

    @Throws(RemoveFavoriteSongException::class)
    override suspend fun removeFavoriteSong(profileId: String, songId: String): Boolean = safeExecute {
        try {
            favoritesRemoteDataSource.removeFavorite(
                profileId = profileId,
                songId = songId
            )
        } catch (ex: RemoveFromFavoritesRemoteException) {
            throw RemoveFavoriteSongException(
                "An error occurred when removing song from favorites",
                ex
            )
        }
    }

    private suspend fun SongDTO.mapToSongBo() =
        let { song ->
            SongMapperData(
                artist = artistRemoteDataSource.getArtistById(song.artist),
                category = categoryRemoteDataSource.getCategoryById(song.category),
                song = song
            )
        }.let(songsMapper::mapInToOut)

    private suspend fun List<SongDTO>.mapToSongBoList() =
        parallelMap { song ->
            SongMapperData(
                artist = artistRemoteDataSource.getArtistById(song.artist),
                category = categoryRemoteDataSource.getCategoryById(song.category),
                song = song
            )
        }.let(songsMapper::mapInListToOutList)
}