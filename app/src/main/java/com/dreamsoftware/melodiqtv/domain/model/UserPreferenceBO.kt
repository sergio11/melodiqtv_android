package com.dreamsoftware.melodiqtv.domain.model

data class UserPreferenceBO(
    val language: AppLanguageEnum,
    val videoQuality: VideoQualityEnum
)
