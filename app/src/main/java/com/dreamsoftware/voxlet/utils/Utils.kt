package com.dreamsoftware.voxlet.utils

import java.time.LocalDateTime
import java.time.ZoneOffset

fun getCurrentTime(): Long = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)

fun formatTimeElapsed(start: Long, end: Long): String {
    val elapsedSeconds = (end - start).coerceAtLeast(0)

    val intervals = listOf(
        60 to "min",
        60 * 60 to "h",
        60 * 60 * 24 to "d",
        60 * 60 * 24 * 7 to "sem",
        60 * 60 * 24 * 30 to "m",
        60 * 60 * 24 * 365 to "a"
    )

    for ((secondsInInterval, label) in intervals) {
        if (elapsedSeconds < secondsInInterval) {
            val value = if (secondsInInterval == 60) elapsedSeconds else elapsedSeconds / secondsInInterval
            return "$value $label"
        }
    }

    return "${elapsedSeconds / (60 * 60 * 24 * 365)} a"
}