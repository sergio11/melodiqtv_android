package com.dreamsoftware.melodiqtv.domain.model

data class UserPreferenceBO(
    val units: UnitsEnum,
    val language: AppLanguageEnum,
    val videoQuality: VideoQualityEnum
)
