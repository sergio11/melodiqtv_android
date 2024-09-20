package com.dreamsoftware.melodiqtv.data.remote.dto.response

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
    val type: String,
    val artist: String,
    val isPremium: Boolean,
    val releasedDate: Timestamp,
    val language: String,
    val duration: Long,
    val isTrending: Boolean,
    val rating: Double? = null,
    val playCount: Long,
    val lyrics: String? = null,
    val videoUrl: String,
    val audioUrl: String
)
