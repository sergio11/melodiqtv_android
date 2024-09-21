package com.dreamsoftware.melodiqtv.data.remote.dto.request

data class SongFilterDTO(
    val language: String?,
    val type: String?,
    val genre: String?,
    val mood: String?,
    val duration: IntRange?,
    val artist: String?,
    val orderByReleasedDateDesc: Boolean = true,
    val priorityFeatured: Boolean = false
)
