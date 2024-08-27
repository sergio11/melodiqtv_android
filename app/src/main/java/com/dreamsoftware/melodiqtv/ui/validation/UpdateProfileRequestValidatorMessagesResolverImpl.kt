package com.dreamsoftware.melodiqtv.ui.validation

import android.content.Context
import com.dreamsoftware.melodiqtv.R
import com.dreamsoftware.melodiqtv.domain.validation.IUpdateProfileRequestValidatorMessagesResolver

class UpdateProfileRequestValidatorMessagesResolverImpl(private val context: Context) :
    IUpdateProfileRequestValidatorMessagesResolver {

    override fun getInvalidAliasMessage(): String = context.getString(R.string.invalid_alias_message)
}