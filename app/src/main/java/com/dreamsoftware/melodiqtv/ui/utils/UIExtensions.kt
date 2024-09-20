package com.dreamsoftware.melodiqtv.ui.utils

import android.content.Context
import com.dreamsoftware.melodiqtv.R
import com.dreamsoftware.melodiqtv.domain.model.AvatarTypeEnum
import com.dreamsoftware.melodiqtv.domain.model.SongBO
import com.dreamsoftware.melodiqtv.domain.model.SongTypeEnum
import com.dreamsoftware.melodiqtv.domain.model.SubscriptionBO

fun SongBO?.formatTimeAndTypeTraining(): String =
    this?.run { "$duration | ${type.value} " }.orEmpty()

val String.Companion.EMPTY: String
    get() = ""

val Char.Companion.SPACE: Char
    get() = ' '


fun SongTypeEnum.getStartButtonID() = when (this) {
    SongTypeEnum.STUDIO -> R.string.start_program
    SongTypeEnum.LIVE -> R.string.start_program
    SongTypeEnum.ACOUSTIC -> R.string.start_program
    SongTypeEnum.REMIX -> R.string.start_program
}

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