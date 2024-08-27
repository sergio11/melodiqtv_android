package com.dreamsoftware.melodiqtv.domain.validation

interface ISignInValidationMessagesResolver {
    fun getInvalidEmailMessage(): String
    fun getShortPasswordMessage(minLength: Int): String
}