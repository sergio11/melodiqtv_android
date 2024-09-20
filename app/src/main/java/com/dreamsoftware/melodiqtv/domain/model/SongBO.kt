package com.dreamsoftware.melodiqtv.domain.model

data class SongBO(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val duration: Long,
    val artistName: String,
    val videoUrl: String,
    val audioUrl: String
)