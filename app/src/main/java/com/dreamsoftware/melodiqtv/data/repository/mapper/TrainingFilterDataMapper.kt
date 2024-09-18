package com.dreamsoftware.melodiqtv.data.repository.mapper

import com.dreamsoftware.melodiqtv.data.remote.dto.request.SongFilterDTO
import com.dreamsoftware.melodiqtv.domain.model.LanguageEnum
import com.dreamsoftware.melodiqtv.domain.model.IntensityEnum
import com.dreamsoftware.melodiqtv.domain.model.SortTypeEnum
import com.dreamsoftware.melodiqtv.domain.model.SongFilterDataBO
import com.dreamsoftware.melodiqtv.domain.model.VideoLengthEnum
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper

internal class TrainingFilterDataMapper: IOneSideMapper<SongFilterDataBO, SongFilterDTO> {
    override fun mapInToOut(input: SongFilterDataBO): SongFilterDTO = with(input) {
        SongFilterDTO(
            classLanguage = classLanguage.takeIf { it != LanguageEnum.NOT_SET }?.value,
            workoutType = workoutType.takeIf { it != WorkoutTypeEnum.NOT_SET }?.value,
            intensity = intensity.takeIf { it != IntensityEnum.NOT_SET }?.value,
            videoLength = videoLength.takeIf { it != VideoLengthEnum.NOT_SET }?.range,
            orderByReleasedDateDesc = sortType == SortTypeEnum.NEWEST || sortType == SortTypeEnum.NOT_SET,
            instructor = artist.takeIf { it.isNotBlank() },
            priorityFeatured = sortType == SortTypeEnum.RELEVANCE
        )
    }

    override fun mapInListToOutList(input: Iterable<SongFilterDataBO>): Iterable<SongFilterDTO> =
        input.map(::mapInToOut)
}