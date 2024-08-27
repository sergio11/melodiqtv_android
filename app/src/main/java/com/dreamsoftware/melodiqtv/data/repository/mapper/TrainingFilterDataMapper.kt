package com.dreamsoftware.melodiqtv.data.repository.mapper

import com.dreamsoftware.melodiqtv.data.remote.dto.request.TrainingFilterDTO
import com.dreamsoftware.melodiqtv.domain.model.ClassLanguageEnum
import com.dreamsoftware.melodiqtv.domain.model.IntensityEnum
import com.dreamsoftware.melodiqtv.domain.model.SortTypeEnum
import com.dreamsoftware.melodiqtv.domain.model.TrainingFilterDataBO
import com.dreamsoftware.melodiqtv.domain.model.VideoLengthEnum
import com.dreamsoftware.melodiqtv.domain.model.WorkoutTypeEnum
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper

internal class TrainingFilterDataMapper: IOneSideMapper<TrainingFilterDataBO, TrainingFilterDTO> {
    override fun mapInToOut(input: TrainingFilterDataBO): TrainingFilterDTO = with(input) {
        TrainingFilterDTO(
            classLanguage = classLanguage.takeIf { it != ClassLanguageEnum.NOT_SET }?.value,
            workoutType = workoutType.takeIf { it != WorkoutTypeEnum.NOT_SET }?.value,
            intensity = intensity.takeIf { it != IntensityEnum.NOT_SET }?.value,
            videoLength = videoLength.takeIf { it != VideoLengthEnum.NOT_SET }?.range,
            orderByReleasedDateDesc = sortType == SortTypeEnum.NEWEST || sortType == SortTypeEnum.NOT_SET,
            instructor = instructor.takeIf { it.isNotBlank() },
            priorityFeatured = sortType == SortTypeEnum.RELEVANCE
        )
    }

    override fun mapInListToOutList(input: Iterable<TrainingFilterDataBO>): Iterable<TrainingFilterDTO> =
        input.map(::mapInToOut)
}