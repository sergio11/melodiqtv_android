package com.dreamsoftware.melodiqtv.utils

import kotlin.enums.enumEntries

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T : Enum<T>> enumNameOfOrDefault(name: String, default: T): T =
    enumEntries<T>().find { it.name.equals(name, ignoreCase = true) } ?: default

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T : Enum<T>> enumOfOrDefault(predicate: (T) -> Boolean, default: T): T =
    enumEntries<T>().find(predicate) ?: default

inline fun <T1 : Any, T2 : Any, R> combinedLet(value1: T1?, value2: T2?, block: (T1, T2) -> R): R? =
    if (value1 != null && value2 != null) {
        block(value1, value2)
    } else {
        null
    }