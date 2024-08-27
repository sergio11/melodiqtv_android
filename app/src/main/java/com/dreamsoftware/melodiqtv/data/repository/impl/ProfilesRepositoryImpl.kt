package com.dreamsoftware.melodiqtv.data.repository.impl

import com.dreamsoftware.melodiqtv.data.preferences.datasource.IProfileSessionDataSource
import com.dreamsoftware.melodiqtv.data.preferences.dto.ProfileSelectedPreferenceDTO
import com.dreamsoftware.melodiqtv.data.remote.datasource.IFavoritesRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.datasource.IProfilesRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.datasource.IUserRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.dto.request.CreateProfileRequestDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.request.PinVerificationRequestDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.request.UpdatedProfileRequestDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.response.ProfileDTO
import com.dreamsoftware.melodiqtv.data.remote.exception.CreateProfileRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.DeleteProfileRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchCategoriesRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchProfileByIdRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.UpdateProfileRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.VerifyProfileRemoteException
import com.dreamsoftware.melodiqtv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.melodiqtv.domain.exception.CreateProfileException
import com.dreamsoftware.melodiqtv.domain.exception.DeleteProfileException
import com.dreamsoftware.melodiqtv.domain.exception.FetchCategoriesException
import com.dreamsoftware.melodiqtv.domain.exception.FetchProfilesByUserException
import com.dreamsoftware.melodiqtv.domain.exception.GetProfileByIdException
import com.dreamsoftware.melodiqtv.domain.exception.GetProfileSelectedException
import com.dreamsoftware.melodiqtv.domain.exception.SelectProfileException
import com.dreamsoftware.melodiqtv.domain.exception.UpdateProfileException
import com.dreamsoftware.melodiqtv.domain.exception.VerifyPinException
import com.dreamsoftware.melodiqtv.domain.model.CreateProfileRequestBO
import com.dreamsoftware.melodiqtv.domain.model.ProfileBO
import com.dreamsoftware.melodiqtv.domain.model.UpdatedProfileRequestBO
import com.dreamsoftware.melodiqtv.domain.repository.IProfilesRepository
import com.dreamsoftware.melodiqtv.utils.IMapper
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper
import kotlinx.coroutines.CoroutineDispatcher

internal class ProfilesRepositoryImpl(
    private val profilesRemoteDataSource: IProfilesRemoteDataSource,
    private val userRemoteDataSource: IUserRemoteDataSource,
    private val favoritesRemoteDataSource: IFavoritesRemoteDataSource,
    private val profilesMapper: IOneSideMapper<ProfileDTO, ProfileBO>,
    private val createProfileMapper: IOneSideMapper<CreateProfileRequestBO, CreateProfileRequestDTO>,
    private val updateProfileMapper: IOneSideMapper<UpdatedProfileRequestBO, UpdatedProfileRequestDTO>,
    private val profileSessionMapper: IMapper<ProfileBO, ProfileSelectedPreferenceDTO>,
    private val profileSessionDataSource: IProfileSessionDataSource,
    dispatcher: CoroutineDispatcher
): SupportRepositoryImpl(dispatcher), IProfilesRepository {

    @Throws(FetchProfilesByUserException::class)
    override suspend fun getProfilesByUser(userId: String): List<ProfileBO> = safeExecute {
        try {
            profilesRemoteDataSource
                .getProfilesByUser(userId)
                .let(profilesMapper::mapInListToOutList)
                .toList()
        } catch (ex: FetchCategoriesRemoteException) {
            throw FetchCategoriesException("An error occurred when fetching profiles", ex)
        }
    }

    @Throws(FetchProfilesByUserException::class)
    override suspend fun countProfilesByUser(userId: String): Long = safeExecute {
        try {
            profilesRemoteDataSource
                .countProfilesByUser(userId)
        } catch (ex: FetchCategoriesRemoteException) {
            throw FetchCategoriesException("An error occurred when fetching profiles", ex)
        }
    }

    @Throws(UpdateProfileException::class)
    override suspend fun updateProfile(
        profileId: String,
        data: UpdatedProfileRequestBO
    ): ProfileBO = safeExecute {
        try {
            profilesRemoteDataSource
                .updateProfile(profileId, updateProfileMapper.mapInToOut(data))
                .let(profilesMapper::mapInToOut)
        } catch (ex: UpdateProfileRemoteException) {
            throw UpdateProfileException("An error occurred when updating profile", ex)
        }
    }

    @Throws(DeleteProfileException::class)
    override suspend fun deleteProfile(profileId: String): Boolean = safeExecute {
        try {
            with(profilesRemoteDataSource) {
                val profile = getProfileById(profileId)
                deleteProfile(profileId).also {
                    favoritesRemoteDataSource.removeFavorites(profileId)
                    userRemoteDataSource.decrementProfilesCount(profile.userId)
                }
            }
        } catch (ex: DeleteProfileRemoteException) {
            throw DeleteProfileException("An error occurred when deleting profile", ex)
        }
    }

    @Throws(CreateProfileException::class)
    override suspend fun createProfile(data: CreateProfileRequestBO): Boolean = safeExecute {
        try {
            profilesRemoteDataSource
                .createProfile(createProfileMapper.mapInToOut(data)).also {
                    userRemoteDataSource.incrementProfilesCount(data.userId)
                }
        } catch (ex: CreateProfileRemoteException) {
            throw CreateProfileException("An error occurred when creating profiles", ex)
        }
    }

    @Throws(SelectProfileException::class)
    override suspend fun selectProfile(profile: ProfileBO) : Unit = safeExecute {
        profileSessionDataSource.save(profileSessionMapper.mapInToOut(profile))
    }

    @Throws(VerifyPinException::class)
    override suspend fun verifyPin(profileId: String, pin: Int): Unit = safeExecute {
        try {
            profilesRemoteDataSource
                .verifyPin(profileId, PinVerificationRequestDTO(pin)).let { isSuccess ->
                    if(!isSuccess) {
                        throw VerifyPinException("Pin verification failed for profile $profileId")
                    }
                }
        } catch (ex: VerifyProfileRemoteException) {
            throw VerifyPinException("An error occurred when verifying pin profile", ex)
        }
    }

    @Throws(GetProfileByIdException::class)
    override suspend fun getProfileById(profileId: String): ProfileBO = safeExecute {
        try {
            profilesRemoteDataSource
                .getProfileById(profileId)
                .let(profilesMapper::mapInToOut)
        } catch (ex: FetchProfileByIdRemoteException) {
            throw GetProfileByIdException("An error occurred when getting profile by id", ex)
        }
    }

    @Throws(GetProfileSelectedException::class)
    override suspend fun getProfileSelectedByUser(userId: String): ProfileBO = safeExecute {
        profileSessionMapper.mapOutToIn(profileSessionDataSource.get())
    }
}