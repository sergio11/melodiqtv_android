package com.dreamsoftware.melodiqtv.data.remote.dto.request

data class SongFilterDTO(
    val classLanguage: String?,
    val workoutType: String?,
    val intensity: String?,
    val videoLength: IntRange?,
    val instructor: String?,
    val orderByReleasedDateDesc: Boolean = true,
    val priorityFeatured: Boolean = false
)
