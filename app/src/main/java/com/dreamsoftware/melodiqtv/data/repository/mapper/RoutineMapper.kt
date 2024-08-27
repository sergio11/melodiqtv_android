package com.dreamsoftware.melodiqtv.data.repository.mapper

import com.dreamsoftware.melodiqtv.data.remote.dto.response.InstructorDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.response.RoutineDTO
import com.dreamsoftware.melodiqtv.domain.model.IntensityEnum
import com.dreamsoftware.melodiqtv.domain.model.LanguageEnum
import com.dreamsoftware.melodiqtv.domain.model.RoutineBO
import com.dreamsoftware.melodiqtv.domain.model.WorkoutTypeEnum
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper
import com.dreamsoftware.melodiqtv.utils.enumNameOfOrDefault

internal class RoutineMapper : IOneSideMapper<Pair<RoutineDTO, InstructorDTO>, RoutineBO> {

    override fun mapInToOut(input: Pair<RoutineDTO, InstructorDTO>): RoutineBO = with(input) {
        RoutineBO(
            id = first.id,
            name = first.name,
            description = first.description,
            instructorName = second.name,
            instructorId = second.id,
            workoutType = enumNameOfOrDefault(first.workoutType, WorkoutTypeEnum.YOGA),
            imageUrl = first.imageUrl,
            duration = first.duration,
            videoUrl = first.videoUrl,
            intensity = enumNameOfOrDefault(first.intensity, IntensityEnum.EASY),
            releasedDate = first.releasedDate,
            song = first.song,
            isPremium = first.isPremium,
            language = enumNameOfOrDefault(first.language, LanguageEnum.ENGLISH)
        )
    }

    override fun mapInListToOutList(input: Iterable<Pair<RoutineDTO, InstructorDTO>>): Iterable<RoutineBO> =
        input.map(::mapInToOut)
}