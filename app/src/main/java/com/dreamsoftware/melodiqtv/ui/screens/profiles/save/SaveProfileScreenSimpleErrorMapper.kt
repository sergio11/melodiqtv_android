package com.dreamsoftware.melodiqtv.ui.screens.profiles.save

import android.content.Context
import com.dreamsoftware.melodiqtv.R
import com.dreamsoftware.melodiqtv.domain.exception.InvalidDataException
import com.dreamsoftware.melodiqtv.domain.exception.UserProfilesLimitReachedException
import com.dreamsoftware.fudge.core.IFudgeTvErrorMapper

class SaveProfileScreenSimpleErrorMapper(
    private val context: Context
): IFudgeTvErrorMapper {
    override fun mapToMessage(ex: Throwable): String = with(context) {
        when(ex) {
            is InvalidDataException -> getString(R.string.save_profile_form_invalid_data_provided)
            is UserProfilesLimitReachedException -> getString(R.string.save_profile_limit_reached_error, ex.maxProfilesLimit)
            else -> getString(R.string.generic_error_exception)
        }
    }
}