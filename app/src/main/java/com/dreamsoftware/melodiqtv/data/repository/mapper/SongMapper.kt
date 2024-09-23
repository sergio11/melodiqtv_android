package com.dreamsoftware.melodiqtv.data.repository.mapper

import com.dreamsoftware.melodiqtv.data.remote.dto.response.ArtistDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.response.CategoryDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.response.SongDTO
import com.dreamsoftware.melodiqtv.domain.model.LanguageEnum
import com.dreamsoftware.melodiqtv.domain.model.SongBO
import com.dreamsoftware.melodiqtv.domain.model.SongGenreEnum
import com.dreamsoftware.melodiqtv.domain.model.SongMoodEnum
import com.dreamsoftware.melodiqtv.domain.model.SongTypeEnum
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper
import com.dreamsoftware.melodiqtv.utils.enumNameOfOrDefault

internal class SongMapper : IOneSideMapper<SongMapperData, SongBO> {

    override fun mapInToOut(input: SongMapperData): SongBO = with(input) {
        SongBO(
            id = song.id,
            title = song.title,
            description = song.description,
            imageUrl = song.imageUrl,
            duration = song.duration,
            artistId = artist.id,
            artistName = artist.name,
            videoUrl = song.videoUrl,
            audioUrl = song.audioUrl,
            type = enumNameOfOrDefault(song.type, SongTypeEnum.ACOUSTIC),
            releasedDate = song.releasedDate.toDate(),
            category = category.title,
            album = song.album,
            genre = enumNameOfOrDefault(song.genre, SongGenreEnum.OTHER),
            isPremium = song.isPremium,
            language = enumNameOfOrDefault(song.language, LanguageEnum.ENGLISH),
            isTrending = song.isTrending,
            rating = song.rating,
            playCount = song.playCount,
            lyrics = song.lyrics,
            mood = enumNameOfOrDefault(song.mood, SongMoodEnum.HAPPY),
        )
    }

    override fun mapInListToOutList(input: Iterable<SongMapperData>): Iterable<SongBO> =
        input.map(::mapInToOut)
}

data class SongMapperData(
    val artist: ArtistDTO,
    val song: SongDTO,
    val category: CategoryDTO
)
