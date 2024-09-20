package com.dreamsoftware.melodiqtv.domain.model

import java.util.Date

data class SongBO(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val duration: Long,
    val artistName: String,
    val videoUrl: String,
    val audioUrl: String,
    val type: SongTypeEnum,
    val releasedDate: Date,
    val category: String,
    val album: String? = null,
    val genre: SongGenreEnum,
    val isPremium: Boolean,
    val language: LanguageEnum,
    val isTrending: Boolean,
    val rating: Double? = null,
    val playCount: Long,
    val lyrics: String? = null,
    val mood: SongMoodEnum
)