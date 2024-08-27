package com.dreamsoftware.melodiqtv.data.preferences.datasource

import com.dreamsoftware.melodiqtv.data.preferences.dto.UserPreferencesDTO
import com.dreamsoftware.melodiqtv.data.preferences.exception.FetchUserPreferencesLocalException
import com.dreamsoftware.melodiqtv.data.preferences.exception.SaveUserPreferencesLocalException

interface IUserPreferencesDataSource {

    @Throws(SaveUserPreferencesLocalException::class)
    suspend fun save(data: UserPreferencesDTO)

    @Throws(FetchUserPreferencesLocalException::class)
    suspend fun get(): UserPreferencesDTO
}