package com.dreamsoftware.melodiqtv.domain.repository

import com.dreamsoftware.melodiqtv.domain.exception.CreateProfileException
import com.dreamsoftware.melodiqtv.domain.exception.DeleteProfileException
import com.dreamsoftware.melodiqtv.domain.exception.FetchProfilesByUserException
import com.dreamsoftware.melodiqtv.domain.exception.GetProfileByIdException
import com.dreamsoftware.melodiqtv.domain.exception.GetProfileSelectedException
import com.dreamsoftware.melodiqtv.domain.exception.SelectProfileException
import com.dreamsoftware.melodiqtv.domain.exception.UpdateProfileException
import com.dreamsoftware.melodiqtv.domain.exception.VerifyPinException
import com.dreamsoftware.melodiqtv.domain.model.CreateProfileRequestBO
import com.dreamsoftware.melodiqtv.domain.model.ProfileBO
import com.dreamsoftware.melodiqtv.domain.model.UpdatedProfileRequestBO

interface IProfilesRepository {

    @Throws(FetchProfilesByUserException::class)
    suspend fun getProfilesByUser(userId: String): List<ProfileBO>

    @Throws(FetchProfilesByUserException::class)
    suspend fun countProfilesByUser(userId: String): Long

    @Throws(UpdateProfileException::class)
    suspend fun updateProfile(profileId: String, data: UpdatedProfileRequestBO): ProfileBO

    @Throws(DeleteProfileException::class)
    suspend fun deleteProfile(profileId: String): Boolean

    @Throws(CreateProfileException::class)
    suspend fun createProfile(data: CreateProfileRequestBO): Boolean

    @Throws(SelectProfileException::class)
    suspend fun selectProfile(profile: ProfileBO)

    @Throws(VerifyPinException::class)
    suspend fun verifyPin(profileId: String, pin: Int)

    @Throws(GetProfileByIdException::class)
    suspend fun getProfileById(profileId: String): ProfileBO

    @Throws(GetProfileSelectedException::class)
    suspend fun getProfileSelectedByUser(userId: String): ProfileBO
}