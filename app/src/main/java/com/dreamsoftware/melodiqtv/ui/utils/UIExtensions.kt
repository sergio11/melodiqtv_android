package com.dreamsoftware.melodiqtv.ui.utils

import android.content.Context
import com.dreamsoftware.melodiqtv.R
import com.dreamsoftware.melodiqtv.domain.model.AvatarTypeEnum
import com.dreamsoftware.melodiqtv.domain.model.SongBO
import com.dreamsoftware.melodiqtv.domain.model.SubscriptionBO
import java.util.Locale

fun SongBO?.formatTimeAndType(): String =
    this?.run { "$duration | ${type.value} " }.orEmpty()

fun Long.formatDuration(): String {
    val minutes = this / 60
    val seconds = this % 60
    return String.format(Locale.getDefault(), "%d:%02d min", minutes, seconds)
}

val String.Companion.EMPTY: String
    get() = ""

val Char.Companion.SPACE: Char
    get() = ' '

fun AvatarTypeEnum.toDrawableResource(): Int =
    when(this) {
        AvatarTypeEnum.BOY -> R.drawable.profile_avatar_boy
        AvatarTypeEnum.GIRL -> R.drawable.profile_avatar_girl
        AvatarTypeEnum.WOMAN -> R.drawable.profile_avatar_woman
        AvatarTypeEnum.MAN -> R.drawable.profile_avatar_man
    }

fun SubscriptionBO.formatPeriodTimeAndPrice(periodTime: String, price: String, context: Context): String =
    "${context.getString(R.string.free_trail)} $price / ${
        if (periodTime == "1")
            "${context.getString(R.string.month)}.\n"
        else
            "$periodTime \n${context.getString(R.string.months)}."
    }${context.getString(R.string.subscription_cancelled)}"

fun SubscriptionBO.formatPeriodTime(periodTime: String, context: Context): String =
    "$periodTime ${context.getString(R.string.month_subscription)}"