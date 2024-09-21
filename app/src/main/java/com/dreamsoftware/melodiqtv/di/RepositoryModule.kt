package com.dreamsoftware.melodiqtv.di

import com.dreamsoftware.melodiqtv.data.preferences.datasource.IProfileSessionDataSource
import com.dreamsoftware.melodiqtv.data.preferences.datasource.IUserPreferencesDataSource
import com.dreamsoftware.melodiqtv.data.preferences.dto.ProfileSelectedPreferenceDTO
import com.dreamsoftware.melodiqtv.data.preferences.dto.UserPreferencesDTO
import com.dreamsoftware.melodiqtv.data.remote.datasource.IAuthRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.datasource.ICategoryRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.datasource.IFavoritesRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.datasource.IArtistsRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.datasource.IProfilesRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.datasource.ISongRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.datasource.ISubscriptionsRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.datasource.IUserRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.datasource.IUserSubscriptionsRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.dto.request.AddFavoriteSongDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.request.AddUserSubscriptionDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.request.CreateProfileRequestDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.request.CreateUserDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.request.SongFilterDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.request.UpdatedProfileRequestDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.request.UpdatedUserRequestDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.response.CategoryDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.response.ArtistDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.response.ProfileDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.response.SubscriptionDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.response.UserResponseDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.response.UserSubscriptionDTO
import com.dreamsoftware.melodiqtv.data.remote.mapper.ProfileSessionMapper
import com.dreamsoftware.melodiqtv.data.repository.impl.CategoryRepositoryImpl
import com.dreamsoftware.melodiqtv.data.repository.impl.ArtistRepositoryImpl
import com.dreamsoftware.melodiqtv.data.repository.impl.ProfilesRepositoryImpl
import com.dreamsoftware.melodiqtv.data.repository.impl.SubscriptionsRepositoryImpl
import com.dreamsoftware.melodiqtv.data.repository.impl.SongRepositoryImpl
import com.dreamsoftware.melodiqtv.data.repository.impl.UserRepositoryImpl
import com.dreamsoftware.melodiqtv.data.repository.mapper.AddFavoriteSongMapper
import com.dreamsoftware.melodiqtv.data.repository.mapper.AddUserSubscriptionMapper
import com.dreamsoftware.melodiqtv.data.repository.mapper.CategoryMapper
import com.dreamsoftware.melodiqtv.data.repository.mapper.CreateProfileRequestMapper
import com.dreamsoftware.melodiqtv.data.repository.mapper.CreateUserMapper
import com.dreamsoftware.melodiqtv.data.repository.mapper.ArtistMapper
import com.dreamsoftware.melodiqtv.data.repository.mapper.ProfileMapper
import com.dreamsoftware.melodiqtv.data.repository.mapper.SubscriptionMapper
import com.dreamsoftware.melodiqtv.data.repository.mapper.SongFilterDataMapper
import com.dreamsoftware.melodiqtv.data.repository.mapper.SongMapper
import com.dreamsoftware.melodiqtv.data.repository.mapper.SongMapperData
import com.dreamsoftware.melodiqtv.data.repository.mapper.UpdateProfileRequestMapper
import com.dreamsoftware.melodiqtv.data.repository.mapper.UpdatedUserRequestMapper
import com.dreamsoftware.melodiqtv.data.repository.mapper.UserDetailMapper
import com.dreamsoftware.melodiqtv.data.repository.mapper.UserPreferencesMapper
import com.dreamsoftware.melodiqtv.data.repository.mapper.UserSubscriptionMapper
import com.dreamsoftware.melodiqtv.domain.model.AddFavoriteSongBO
import com.dreamsoftware.melodiqtv.domain.model.AddUserSubscriptionBO
import com.dreamsoftware.melodiqtv.domain.model.CategoryBO
import com.dreamsoftware.melodiqtv.domain.model.CreateProfileRequestBO
import com.dreamsoftware.melodiqtv.domain.model.ArtistBO
import com.dreamsoftware.melodiqtv.domain.model.SignUpBO
import com.dreamsoftware.melodiqtv.domain.model.ProfileBO
import com.dreamsoftware.melodiqtv.domain.model.SongBO
import com.dreamsoftware.melodiqtv.domain.model.SubscriptionBO
import com.dreamsoftware.melodiqtv.domain.model.SongFilterDataBO
import com.dreamsoftware.melodiqtv.domain.model.UpdatedProfileRequestBO
import com.dreamsoftware.melodiqtv.domain.model.UpdatedUserRequestBO
import com.dreamsoftware.melodiqtv.domain.model.UserDetailBO
import com.dreamsoftware.melodiqtv.domain.model.UserPreferenceBO
import com.dreamsoftware.melodiqtv.domain.model.UserSubscriptionBO
import com.dreamsoftware.melodiqtv.domain.repository.ICategoryRepository
import com.dreamsoftware.melodiqtv.domain.repository.IArtistRepository
import com.dreamsoftware.melodiqtv.domain.repository.IProfilesRepository
import com.dreamsoftware.melodiqtv.domain.repository.ISubscriptionsRepository
import com.dreamsoftware.melodiqtv.domain.repository.ISongRepository
import com.dreamsoftware.melodiqtv.domain.repository.IUserRepository
import com.dreamsoftware.melodiqtv.utils.IMapper
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideSongMapper(): IOneSideMapper<SongMapperData, SongBO> = SongMapper()

    @Provides
    @Singleton
    fun provideCategoryMapper(): IOneSideMapper<CategoryDTO, CategoryBO> = CategoryMapper()

    @Provides
    @Singleton
    fun provideProfileMapper(): IOneSideMapper<ProfileDTO, ProfileBO> = ProfileMapper()

    @Provides
    @Singleton
    fun provideCreateProfileRequestMapper(): IOneSideMapper<CreateProfileRequestBO, CreateProfileRequestDTO> = CreateProfileRequestMapper()

    @Provides
    @Singleton
    fun provideUpdateProfileRequestMapper(): IOneSideMapper<UpdatedProfileRequestBO, UpdatedProfileRequestDTO> = UpdateProfileRequestMapper()

    @Provides
    @Singleton
    fun provideProfileSessionMapper(): IMapper<ProfileBO, ProfileSelectedPreferenceDTO> = ProfileSessionMapper()

    @Provides
    @Singleton
    fun provideUserDetailMapper(): IOneSideMapper<UserResponseDTO, UserDetailBO> = UserDetailMapper()

    @Provides
    @Singleton
    fun provideUpdatedUserRequestMapper(): IOneSideMapper<UpdatedUserRequestBO, UpdatedUserRequestDTO> = UpdatedUserRequestMapper()

    @Provides
    @Singleton
    fun provideUpdatedCreateUserMapper(): IOneSideMapper<SignUpBO, CreateUserDTO> = CreateUserMapper()

    @Provides
    @Singleton
    fun provideAddFavoriteSongMapper(): IOneSideMapper<AddFavoriteSongBO, AddFavoriteSongDTO> = AddFavoriteSongMapper()

    @Provides
    @Singleton
    fun provideSongFilterDataMapper(): IOneSideMapper<SongFilterDataBO, SongFilterDTO> = SongFilterDataMapper()

    @Provides
    @Singleton
    fun provideSubscriptionMapper(): IOneSideMapper<SubscriptionDTO, SubscriptionBO> = SubscriptionMapper()

    @Provides
    @Singleton
    fun provideAddUserSubscriptionMapper(): IOneSideMapper<AddUserSubscriptionBO, AddUserSubscriptionDTO> = AddUserSubscriptionMapper()

    @Provides
    @Singleton
    fun provideUserSubscriptionMapper():  IOneSideMapper<UserSubscriptionDTO, UserSubscriptionBO> = UserSubscriptionMapper()

    @Provides
    @Singleton
    fun provideArtistMapper(): IOneSideMapper<ArtistDTO, ArtistBO> = ArtistMapper()

    @Provides
    @Singleton
    fun provideUserPreferencesMapper(): IMapper<UserPreferencesDTO, UserPreferenceBO> = UserPreferencesMapper()

    @Provides
    @Singleton
    fun provideArtistRepository(
        instructorsRemoteDataSource: IArtistsRemoteDataSource,
        instructorMapper: IOneSideMapper<ArtistDTO, ArtistBO>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IArtistRepository =
        ArtistRepositoryImpl(
            instructorsRemoteDataSource,
            instructorMapper,
            dispatcher
        )

    @Provides
    @Singleton
    fun provideSongRepository(
        songRemoteDataSource: ISongRemoteDataSource,
        favoritesRemoteDataSource: IFavoritesRemoteDataSource,
        artistRemoteDataSource: IArtistsRemoteDataSource,
        categoryRemoteDataSource: ICategoryRemoteDataSource,
        songsMapper: IOneSideMapper<SongMapperData, SongBO>,
        addFavoriteMapper: IOneSideMapper<AddFavoriteSongBO, AddFavoriteSongDTO>,
        filterDataMapper: IOneSideMapper<SongFilterDataBO, SongFilterDTO>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): ISongRepository =
        SongRepositoryImpl(
            songRemoteDataSource,
            favoritesRemoteDataSource,
            artistRemoteDataSource,
            categoryRemoteDataSource,
            songsMapper,
            addFavoriteMapper,
            filterDataMapper,
            dispatcher
        )

    @Provides
    @Singleton
    fun provideUserRepository(
        userRemoteDataSource: IUserRemoteDataSource,
        authRemoteDataSource: IAuthRemoteDataSource,
        userPreferencesDataSource: IUserPreferencesDataSource,
        userDetailMapper: IOneSideMapper<UserResponseDTO, UserDetailBO>,
        updatedUserRequestMapper: IOneSideMapper<UpdatedUserRequestBO, UpdatedUserRequestDTO>,
        userPreferencesMapper: IMapper<UserPreferencesDTO, UserPreferenceBO>,
        createUserMapper: IOneSideMapper<SignUpBO, CreateUserDTO>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IUserRepository =
        UserRepositoryImpl(
            userRemoteDataSource,
            authRemoteDataSource,
            userPreferencesDataSource,
            userDetailMapper,
            userPreferencesMapper,
            updatedUserRequestMapper,
            createUserMapper,
            dispatcher
        )

    @Provides
    @Singleton
    fun provideCategoryRepository(
        categoryRemoteDataSource: ICategoryRemoteDataSource,
        categoryMapper: IOneSideMapper<CategoryDTO, CategoryBO>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): ICategoryRepository =
        CategoryRepositoryImpl(
            categoryRemoteDataSource,
            categoryMapper,
            dispatcher
        )


    @Provides
    @Singleton
    fun provideProfilesRepository(
        profilesRemoteDataSource: IProfilesRemoteDataSource,
        userRemoteDataSource: IUserRemoteDataSource,
        favoritesRemoteDataSource: IFavoritesRemoteDataSource,
        profilesMapper: IOneSideMapper<ProfileDTO, ProfileBO>,
        createProfileMapper: IOneSideMapper<CreateProfileRequestBO, CreateProfileRequestDTO>,
        updateProfileMapper: IOneSideMapper<UpdatedProfileRequestBO, UpdatedProfileRequestDTO>,
        profileSessionMapper: IMapper<ProfileBO, ProfileSelectedPreferenceDTO>,
        profileSessionDataSource: IProfileSessionDataSource,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IProfilesRepository =
        ProfilesRepositoryImpl(
            profilesRemoteDataSource,
            userRemoteDataSource,
            favoritesRemoteDataSource,
            profilesMapper,
            createProfileMapper,
            updateProfileMapper,
            profileSessionMapper,
            profileSessionDataSource,
            dispatcher
        )

    @Provides
    @Singleton
    fun provideSubscriptionsRepository(
        userSubscriptionRemoteDataSource: IUserSubscriptionsRemoteDataSource,
        subscriptionsRemoteDataSource: ISubscriptionsRemoteDataSource,
        subscriptionMapper: IOneSideMapper<SubscriptionDTO, SubscriptionBO>,
        addUserSubscriptionMapper: IOneSideMapper<AddUserSubscriptionBO, AddUserSubscriptionDTO>,
        userSubscriptionMapper: IOneSideMapper<UserSubscriptionDTO, UserSubscriptionBO>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): ISubscriptionsRepository =
        SubscriptionsRepositoryImpl(
            userSubscriptionRemoteDataSource,
            subscriptionsRemoteDataSource,
            subscriptionMapper,
            addUserSubscriptionMapper,
            userSubscriptionMapper,
            dispatcher
        )
}