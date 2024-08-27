package com.dreamsoftware.melodiqtv.data.repository.mapper

import com.dreamsoftware.melodiqtv.data.preferences.dto.UserPreferencesDTO
import com.dreamsoftware.melodiqtv.domain.model.AppLanguageEnum
import com.dreamsoftware.melodiqtv.domain.model.UnitsEnum
import com.dreamsoftware.melodiqtv.domain.model.UserPreferenceBO
import com.dreamsoftware.melodiqtv.domain.model.VideoQualityEnum
import com.dreamsoftware.melodiqtv.utils.IMapper
import com.dreamsoftware.melodiqtv.utils.enumOfOrDefault

internal class UserPreferencesMapper : IMapper<UserPreferencesDTO, UserPreferenceBO> {

    override fun mapInToOut(input: UserPreferencesDTO): UserPreferenceBO = with(input) {
        UserPreferenceBO(
            units = enumOfOrDefault({ it.value == units}, UnitsEnum.METRIC),
            language = enumOfOrDefault({ it.value == language}, AppLanguageEnum.ENGLISH),
            videoQuality = enumOfOrDefault({ it.value == videoQuality}, VideoQualityEnum.FULL_HD)
        )
    }

    override fun mapInListToOutList(input: Iterable<UserPreferencesDTO>): Iterable<UserPreferenceBO> =
        input.map(::mapInToOut)

    override fun mapOutToIn(input: UserPreferenceBO): UserPreferencesDTO = with(input) {
        UserPreferencesDTO(
            units = units.value,
            language = language.value,
            videoQuality = videoQuality.value
        )
    }

    override fun mapOutListToInList(input: Iterable<UserPreferenceBO>): Iterable<UserPreferencesDTO> =
        input.map(::mapOutToIn)
}