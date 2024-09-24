package com.dreamsoftware.melodiqtv.data.repository.mapper

import com.dreamsoftware.melodiqtv.data.remote.dto.request.SongFilterDTO
import com.dreamsoftware.melodiqtv.domain.model.DurationEnum
import com.dreamsoftware.melodiqtv.domain.model.LanguageEnum
import com.dreamsoftware.melodiqtv.domain.model.SongFilterDataBO
import com.dreamsoftware.melodiqtv.domain.model.SongGenreEnum
import com.dreamsoftware.melodiqtv.domain.model.SongMoodEnum
import com.dreamsoftware.melodiqtv.domain.model.SongTypeEnum
import com.dreamsoftware.melodiqtv.domain.model.SortTypeEnum
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper

internal class SongFilterDataMapper: IOneSideMapper<SongFilterDataBO, SongFilterDTO> {

    override fun mapInToOut(input: SongFilterDataBO): SongFilterDTO = with(input) {
        SongFilterDTO(
            language = language.takeIf { it != LanguageEnum.NOT_SET }?.name?.lowercase(),
            type = type.takeIf { it != SongTypeEnum.NOT_SET }?.name?.lowercase(),
            genre = genre.takeIf { it != SongGenreEnum.NOT_SET }?.name?.lowercase(),
            mood = mood.takeIf { it != SongMoodEnum.NOT_SET }?.name?.lowercase(),
            duration = duration.takeIf { it != DurationEnum.NOT_SET }?.range,
            orderByReleasedDateDesc = sortType == SortTypeEnum.NEWEST || sortType == SortTypeEnum.NOT_SET,
            artist = artist.takeIf { it.isNotBlank() },
            priorityFeatured = sortType == SortTypeEnum.RELEVANCE
        )
    }

    override fun mapInListToOutList(input: Iterable<SongFilterDataBO>): Iterable<SongFilterDTO> =
        input.map(::mapInToOut)
}