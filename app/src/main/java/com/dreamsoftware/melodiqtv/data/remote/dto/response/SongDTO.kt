package com.dreamsoftware.melodiqtv.data.remote.dto.response

import java.util.Date

data class SongDTO(
    val id: String,
    val title: String,
    val description: String,
    val category: String,
    val type: String,
    val isPremium: Boolean,
    val releasedDate: Date,
    val language: String,
    val duration: String
)
