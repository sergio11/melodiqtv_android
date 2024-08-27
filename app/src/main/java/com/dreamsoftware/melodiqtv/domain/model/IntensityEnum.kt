package com.dreamsoftware.melodiqtv.domain.model

import com.dreamsoftware.melodiqtv.ui.utils.EMPTY

enum class IntensityEnum(val value: String, val level: String) {
    NOT_SET("Not set", String.EMPTY),
    EASY("Easy", "Intensity •"),
    MEDIUM("Medium", "Intensity ••"),
    HARD("Hard", "Intensity •••")
}