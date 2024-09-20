package com.dreamsoftware.melodiqtv.domain.model

data class SongFilterDataBO(
    val type: SongTypeEnum,
    val classLanguage: LanguageEnum,
    val genre: SongGenreEnum,
    val mood: SongMoodEnum,
    val videoLength: VideoLengthEnum,
    val sortType: SortTypeEnum,
    val artist: String
)
