package com.dreamsoftware.melodiqtv.domain.validation.impl

import com.dreamsoftware.melodiqtv.domain.extensions.isProfileAliasNotValid
import com.dreamsoftware.melodiqtv.domain.extensions.isSecurePinNotValid
import com.dreamsoftware.melodiqtv.domain.model.CreateProfileRequestBO
import com.dreamsoftware.melodiqtv.domain.validation.IBusinessEntityValidator
import com.dreamsoftware.melodiqtv.domain.validation.ICreateProfileRequestValidatorMessagesResolver

internal class CreateProfileRequestValidatorImpl(
    private val messagesResolver: ICreateProfileRequestValidatorMessagesResolver
) : IBusinessEntityValidator<CreateProfileRequestBO> {

    override fun validate(entity: CreateProfileRequestBO): Map<String, String> = buildMap {
        with(entity) {
            if (pin != null && pin.isSecurePinNotValid()) {
                put(CreateProfileRequestBO.FIELD_PIN, messagesResolver.getInvalidPinMessage())
            }
            if (alias.isProfileAliasNotValid()) {
                put(CreateProfileRequestBO.FIELD_ALIAS, messagesResolver.getInvalidAliasMessage())
            }
        }
    }
}