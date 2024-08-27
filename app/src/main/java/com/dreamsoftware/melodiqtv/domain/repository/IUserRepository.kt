package com.dreamsoftware.melodiqtv.domain.repository

import com.dreamsoftware.melodiqtv.domain.exception.GetUserAuthenticatedUidException
import com.dreamsoftware.melodiqtv.domain.exception.GetUserDetailException
import com.dreamsoftware.melodiqtv.domain.exception.GetUserPreferencesException
import com.dreamsoftware.melodiqtv.domain.exception.SaveUserPreferencesException
import com.dreamsoftware.melodiqtv.domain.exception.SignInException
import com.dreamsoftware.melodiqtv.domain.exception.SignOffException
import com.dreamsoftware.melodiqtv.domain.exception.SignUpException
import com.dreamsoftware.melodiqtv.domain.exception.UpdateUserDetailException
import com.dreamsoftware.melodiqtv.domain.exception.VerifySessionException
import com.dreamsoftware.melodiqtv.domain.model.SignInBO
import com.dreamsoftware.melodiqtv.domain.model.SignUpBO
import com.dreamsoftware.melodiqtv.domain.model.UpdatedUserRequestBO
import com.dreamsoftware.melodiqtv.domain.model.UserDetailBO
import com.dreamsoftware.melodiqtv.domain.model.UserPreferenceBO
import kotlin.jvm.Throws

interface IUserRepository {

    @Throws(SignInException::class)
    suspend fun signIn(data: SignInBO): UserDetailBO

    @Throws(SignUpException::class)
    suspend fun signUp(user: SignUpBO): UserDetailBO

    @Throws(SignOffException::class)
    suspend fun signOff()

    @Throws(VerifySessionException::class)
    suspend fun hasSession(): Boolean

    @Throws(GetUserDetailException::class)
    suspend fun getDetail(): UserDetailBO

    @Throws(GetUserAuthenticatedUidException::class)
    suspend fun getAuthenticatedUid(): String

    @Throws(UpdateUserDetailException::class)
    suspend fun updateDetail(data: UpdatedUserRequestBO): UserDetailBO

    @Throws(SaveUserPreferencesException::class)
    suspend fun saveUserPreferences(data: UserPreferenceBO)

    @Throws(GetUserPreferencesException::class)
    suspend fun getUserPreferences(): UserPreferenceBO
}