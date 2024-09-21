package com.dreamsoftware.melodiqtv.data.repository.mapper

import com.dreamsoftware.melodiqtv.data.remote.dto.request.SongFilterDTO
import com.dreamsoftware.melodiqtv.domain.model.LanguageEnum
import com.dreamsoftware.melodiqtv.domain.model.SongFilterDataBO
import com.dreamsoftware.melodiqtv.domain.model.SongGenreEnum
import com.dreamsoftware.melodiqtv.domain.model.SongMoodEnum
import com.dreamsoftware.melodiqtv.domain.model.SongTypeEnum
import com.dreamsoftware.melodiqtv.domain.model.SortTypeEnum
import com.dreamsoftware.melodiqtv.domain.model.VideoLengthEnum
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper

internal class SongFilterDataMapper: IOneSideMapper<SongFilterDataBO, SongFilterDTO> {

    override fun mapInToOut(input: SongFilterDataBO): SongFilterDTO = with(input) {
        SongFilterDTO(
            language = language.takeIf { it != LanguageEnum.NOT_SET }?.value,
            type = type.takeIf { it != SongTypeEnum.NOT_SET }?.value,
            genre = genre.takeIf { it != SongGenreEnum.NOT_SET }?.value,
            mood = mood.takeIf { it != SongMoodEnum.NOT_SET }?.value,
            videoLength = videoLength.takeIf { it != VideoLengthEnum.NOT_SET }?.range,
            orderByReleasedDateDesc = sortType == SortTypeEnum.NEWEST || sortType == SortTypeEnum.NOT_SET,
            artist = artist.takeIf { it.isNotBlank() },
            priorityFeatured = sortType == SortTypeEnum.RELEVANCE
        )
    }

    override fun mapInListToOutList(input: Iterable<SongFilterDataBO>): Iterable<SongFilterDTO> =
        input.map(::mapInToOut)
}