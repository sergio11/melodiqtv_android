package com.dreamsoftware.melodiqtv.data.remote.mapper

import com.dreamsoftware.melodiqtv.data.remote.dto.response.RoutineDTO
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper
import java.util.Date

internal class RoutineRemoteMapper: IOneSideMapper<Map<String, Any?>, RoutineDTO> {

    private companion object {
        const val UID_KEY = "uid"
        const val NAME_KEY = "name"
        const val DESCRIPTION_KEY = "description"
        const val INSTRUCTOR_KEY = "instructor"
        const val WORKOUT_TYPE_KEY = "workoutType"
        const val IMAGE_URL_KEY = "imageUrl"
        const val DURATION_KEY = "duration"
        const val VIDEO_URL_KEY = "videoUrl"
        const val INTENSITY_KEY = "intensity"
        const val RELEASED_DATE_KEY = "releasedDate"
        const val IS_PREMIUM_KEY = "isPremium"
        const val SONG_KEY = "songId"
        const val LANGUAGE_KEY = "language"
        const val CATEGORY_KEY = "category"
    }

    override fun mapInToOut(input: Map<String, Any?>): RoutineDTO = with(input) {
        RoutineDTO(
            id = get(UID_KEY) as String,
            name = get(NAME_KEY) as String,
            description = get(DESCRIPTION_KEY) as String,
            instructor = get(INSTRUCTOR_KEY) as String,
            workoutType = get(WORKOUT_TYPE_KEY) as String,
            imageUrl = get(IMAGE_URL_KEY) as String,
            duration = get(DURATION_KEY) as String,
            videoUrl = get(VIDEO_URL_KEY) as String,
            intensity = get(INTENSITY_KEY) as String,
            releasedDate = Date(),
            isPremium = get(IS_PREMIUM_KEY) as Boolean,
            language = get(LANGUAGE_KEY) as String,
            song = get(SONG_KEY) as String,
            category = get(CATEGORY_KEY) as String
        )
    }

    override fun mapInListToOutList(input: Iterable<Map<String, Any?>>): Iterable<RoutineDTO> =
        input.map(::mapInToOut)
}