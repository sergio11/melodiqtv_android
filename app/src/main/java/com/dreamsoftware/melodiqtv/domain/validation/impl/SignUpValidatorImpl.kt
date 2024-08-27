package com.dreamsoftware.melodiqtv.domain.validation.impl

import android.util.Patterns
import com.dreamsoftware.melodiqtv.domain.model.SignUpBO
import com.dreamsoftware.melodiqtv.domain.validation.IBusinessEntityValidator
import com.dreamsoftware.melodiqtv.domain.validation.ISignUpValidationMessagesResolver

internal class SignUpValidatorImpl(
    private val messagesResolver: ISignUpValidationMessagesResolver
): IBusinessEntityValidator<SignUpBO> {

    private companion object {
        const val MIN_PASSWORD_LENGTH = 6
    }

    override fun validate(entity: SignUpBO): Map<String, String> = buildMap {
        with(entity) {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                put(SignUpBO.FIELD_EMAIL, messagesResolver.getInvalidEmailMessage())
            }
            if (password.length < MIN_PASSWORD_LENGTH) {
                put(SignUpBO.FIELD_PASSWORD, messagesResolver.getShortPasswordMessage(MIN_PASSWORD_LENGTH))
            }
            if (password != confirmPassword) {
                put(SignUpBO.FIELD_CONFIRM_PASSWORD, messagesResolver.getPasswordsDoNotMatchMessage())
            }
        }
    }
}