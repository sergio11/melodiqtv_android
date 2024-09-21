package com.dreamsoftware.melodiqtv.domain.model

enum class DurationEnum(val value: String, val range: IntRange?) {
    NOT_SET("Not set", null),
    SHORT("10-30 min", 10..30),
    MEDIUM("30-45 min", 30..45),
    LONG("45-60 min", 45..60)
}