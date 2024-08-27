package com.dreamsoftware.melodiqtv.domain.validation

interface ICreateProfileRequestValidatorMessagesResolver {
    fun getInvalidPinMessage(): String
    fun getInvalidAliasMessage(): String
}