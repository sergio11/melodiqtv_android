package com.dreamsoftware.melodiqtv.data.remote.dto.response

import com.dreamsoftware.melodiqtv.domain.model.SongTypeEnum
import com.google.firebase.Timestamp

data class SongDTO(
    val id: String,
    val title: String,
    val description: String,
    val category: String,
    val imageUrl: String,
    val album: String? = null,
    val genre: String,
    val mood: String,
    val type: SongTypeEnum,
    val artist: String,
    val isPremium: Boolean,
    val releasedDate: Timestamp,
    val language: String,
    val duration: String,
    val isTrending: Boolean,
    val rating: Double? = null,
    val playCount: Int = 0,
    val lyrics: String? = null,
    val videoUrl: String,
    val audioUrl: String,
)
