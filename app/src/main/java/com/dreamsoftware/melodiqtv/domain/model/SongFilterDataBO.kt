package com.dreamsoftware.melodiqtv.domain.model

data class SongFilterDataBO(
    val type: SongTypeEnum,
    val language: LanguageEnum,
    val genre: SongGenreEnum,
    val mood: SongMoodEnum,
    val duration: DurationEnum,
    val sortType: SortTypeEnum,
    val artist: String
)
