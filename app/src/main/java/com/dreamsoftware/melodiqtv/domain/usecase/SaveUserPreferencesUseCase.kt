package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.melodiqtv.domain.model.AppLanguageEnum
import com.dreamsoftware.melodiqtv.domain.model.UserPreferenceBO
import com.dreamsoftware.melodiqtv.domain.model.VideoQualityEnum
import com.dreamsoftware.melodiqtv.domain.repository.IUserRepository
import com.dreamsoftware.melodiqtv.utils.enumOfOrDefault
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class SaveUserPreferencesUseCase(
    private val userRepository: IUserRepository
) : FudgeTvUseCaseWithParams<SaveUserPreferencesUseCase.Params, Unit>() {

    override suspend fun onExecuted(params: Params): Unit = with(params) {
        userRepository.saveUserPreferences(toUserPreferencesBO())
    }

    private fun Params.toUserPreferencesBO() = UserPreferenceBO(
        language = enumOfOrDefault({it.value == language}, AppLanguageEnum.ENGLISH),
        videoQuality = enumOfOrDefault({it.value == videoQuality}, VideoQualityEnum.FULL_HD)
    )

    data class Params(
        val language: String,
        val videoQuality: String
    )
}