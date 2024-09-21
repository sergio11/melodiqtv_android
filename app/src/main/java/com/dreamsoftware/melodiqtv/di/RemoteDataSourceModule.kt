package com.dreamsoftware.melodiqtv.di

import com.dreamsoftware.melodiqtv.data.remote.datasource.IAuthRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.datasource.ICategoryRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.datasource.IFavoritesRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.datasource.IArtistsRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.datasource.IProfilesRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.datasource.ISongRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.datasource.ISubscriptionsRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.datasource.IUserRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.datasource.IUserSubscriptionsRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.datasource.impl.AuthRemoteDataSourceImpl
import com.dreamsoftware.melodiqtv.data.remote.datasource.impl.CategoryRemoteDataSourceImpl
import com.dreamsoftware.melodiqtv.data.remote.datasource.impl.FavoritesRemoteDataSourceImpl
import com.dreamsoftware.melodiqtv.data.remote.datasource.impl.ArtistsRemoteDataSourceImpl
import com.dreamsoftware.melodiqtv.data.remote.datasource.impl.ProfilesRemoteDataSourceImpl
import com.dreamsoftware.melodiqtv.data.remote.datasource.impl.SongRemoteDataSourceImpl
import com.dreamsoftware.melodiqtv.data.remote.datasource.impl.SubscriptionsRemoteDataSourceImpl
import com.dreamsoftware.melodiqtv.data.remote.datasource.impl.UserRemoteDataSourceImpl
import com.dreamsoftware.melodiqtv.data.remote.datasource.impl.UserSubscriptionsRemoteDataSourceImpl
import com.dreamsoftware.melodiqtv.data.remote.dto.request.AddFavoriteSongDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.request.AddUserSubscriptionDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.request.CreateProfileRequestDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.request.CreateUserDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.request.UpdatedProfileRequestDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.request.UpdatedUserRequestDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.response.AuthUserDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.response.CategoryDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.response.FavoriteSongDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.response.ArtistDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.response.ProfileDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.response.SongDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.response.SubscriptionDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.response.UserResponseDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.response.UserSubscriptionDTO
import com.dreamsoftware.melodiqtv.data.remote.mapper.AddFavoriteSongRemoteMapper
import com.dreamsoftware.melodiqtv.data.remote.mapper.AddUserSubscriptionRemoteMapper
import com.dreamsoftware.melodiqtv.data.remote.mapper.CategoryRemoteMapper
import com.dreamsoftware.melodiqtv.data.remote.mapper.CreateProfileRequestRemoteMapper
import com.dreamsoftware.melodiqtv.data.remote.mapper.CreateUserRequestRemoteMapper
import com.dreamsoftware.melodiqtv.data.remote.mapper.FavoriteSongRemoteMapper
import com.dreamsoftware.melodiqtv.data.remote.mapper.ArtistRemoteMapper
import com.dreamsoftware.melodiqtv.data.remote.mapper.ProfileRemoteMapper
import com.dreamsoftware.melodiqtv.data.remote.mapper.SongRemoteMapper
import com.dreamsoftware.melodiqtv.data.remote.mapper.SubscriptionRemoteMapper
import com.dreamsoftware.melodiqtv.data.remote.mapper.UpdateProfileRequestRemoteMapper
import com.dreamsoftware.melodiqtv.data.remote.mapper.UpdatedUserRequestRemoteMapper
import com.dreamsoftware.melodiqtv.data.remote.mapper.UserAuthenticatedRemoteMapper
import com.dreamsoftware.melodiqtv.data.remote.mapper.UserRemoteMapper
import com.dreamsoftware.melodiqtv.data.remote.mapper.UserSubscriptionsRemoteMapper
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

// Dagger module for providing Firebase-related dependencies
@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {

    /**
     * Provides a singleton instance of UserAuthenticatedMapper.
     * @return a new instance of UserAuthenticatedMapper.
     */
    @Provides
    @Singleton
    fun provideUserAuthenticatedRemoteMapper(): IOneSideMapper<FirebaseUser, AuthUserDTO> = UserAuthenticatedRemoteMapper()

    /**
     * Provides a singleton instance of CategoryMapper.
     * @return a new instance of CategoryMapper.
     */
    @Provides
    @Singleton
    fun provideCategoryRemoteMapper(): IOneSideMapper<Map<String, Any?>, CategoryDTO> = CategoryRemoteMapper()

    /**
     * Provides a singleton instance of CreateProfileRequestRemoteMapper.
     * @return a new instance of CreateProfileRequestRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideCreateProfileRequestRemoteMapper(): IOneSideMapper<CreateProfileRequestDTO, Map<String, Any?>> = CreateProfileRequestRemoteMapper()

    /**
     * Provides a singleton instance of UpdateProfileRequestRemoteMapper.
     * @return a new instance of UpdateProfileRequestRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideUpdateProfileRequestRemoteMapper(): IOneSideMapper<UpdatedProfileRequestDTO, Map<String, Any?>> = UpdateProfileRequestRemoteMapper()

    /**
     * Provides a singleton instance of UserRemoteMapper.
     * @return a new instance of UserRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideUsersRemoteMapper(): IOneSideMapper<Map<String, Any?>, UserResponseDTO> = UserRemoteMapper()

    /**
     * Provides a singleton instance of UpdatedUserRequestRemoteMapper.
     * @return a new instance of UpdatedUserRequestRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideUpdatedUserRequestRemoteMapper(): IOneSideMapper<UpdatedUserRequestDTO, Map<String, Any?>> = UpdatedUserRequestRemoteMapper()

    /**
     * Provides a singleton instance of CreateUserRequestRemoteMapper.
     * @return a new instance of CreateUserRequestRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideCreateUserRequestRemoteMapper(): IOneSideMapper<CreateUserDTO, Map<String, Any?>> = CreateUserRequestRemoteMapper()

    /**
     * Provides a singleton instance of ProfileRemoteMapper.
     * @return a new instance of ProfileRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideProfileRemoteMapper(): IOneSideMapper<Map<String, Any?>, ProfileDTO> = ProfileRemoteMapper()

    /**
     * Provides a singleton instance of AddFavoriteSongRemoteMapper.
     * @return a new instance of AddFavoriteSongRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideAddFavoriteSongRemoteMapper(): IOneSideMapper<AddFavoriteSongDTO, Map<String, Any?>> = AddFavoriteSongRemoteMapper()

    /**
     * Provides a singleton instance of FavoriteSongRemoteMapper.
     * @return a new instance of FavoriteSongRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideFavoriteSongRemoteMapper(): IOneSideMapper<Map<String, Any?>, FavoriteSongDTO> = FavoriteSongRemoteMapper()

    /**
     * Provides a singleton instance of SubscriptionRemoteMapper.
     * @return a new instance of SubscriptionRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideSubscriptionRemoteMapper(): IOneSideMapper<Map<String, Any?>, SubscriptionDTO> = SubscriptionRemoteMapper()

    /**
     * Provides a singleton instance of AddUserSubscriptionRemoteMapper.
     * @return a new instance of AddUserSubscriptionRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideAddUserSubscriptionRemoteMapper(): IOneSideMapper<AddUserSubscriptionDTO, Map<String, Any?>> = AddUserSubscriptionRemoteMapper()

    /**
     * Provides a singleton instance of UserSubscriptionsRemoteMapper.
     * @return a new instance of UserSubscriptionsRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideUserSubscriptionsRemoteMapper(): IOneSideMapper<Map<String, Any?>, UserSubscriptionDTO> = UserSubscriptionsRemoteMapper()

    /**
     * Provides a singleton instance of ArtistRemoteMapper.
     * @return a new instance of ArtistRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideArtistRemoteMapper(): IOneSideMapper<Map<String, Any?>, ArtistDTO> = ArtistRemoteMapper()

    /**
     * Provides a singleton instance of SongRemoteMapper.
     * @return a new instance of SongRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideSongRemoteMapper(): IOneSideMapper<Map<String, Any?>, SongDTO> = SongRemoteMapper()

    /**
     * Provides a singleton instance of FirebaseAuth.
     * @return the default instance of FirebaseAuth.
     */
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    /**
     * Provide Firebase Store
     */
    @Provides
    @Singleton
    fun provideFirebaseStore() = Firebase.firestore

    /**
     * Provides a singleton instance of IAuthDataSource.
     * @param userAuthenticatedMapper the IOneSideMapper<FirebaseUser, AuthUserDTO> instance.
     * @param firebaseAuth the FirebaseAuth instance.
     * @return a new instance of AuthDataSourceImpl implementing IAuthDataSource.
     */
    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(
        userAuthenticatedMapper: IOneSideMapper<FirebaseUser, AuthUserDTO>,
        firebaseAuth: FirebaseAuth
    ): IAuthRemoteDataSource = AuthRemoteDataSourceImpl(
        userAuthenticatedMapper,
        firebaseAuth
    )

    @Provides
    @Singleton
    fun provideCategoryRemoteDataSource(
        categoryMapper: IOneSideMapper<Map<String, Any?>, CategoryDTO>,
        firestore: FirebaseFirestore,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): ICategoryRemoteDataSource = CategoryRemoteDataSourceImpl(
        firestore,
        categoryMapper,
        dispatcher
    )

    @Provides
    @Singleton
    fun provideProfilesRemoteDataSource(
        firebaseStore: FirebaseFirestore,
        profilesMapper: IOneSideMapper<Map<String, Any?>, ProfileDTO>,
        createProfileRequestMapper: IOneSideMapper<CreateProfileRequestDTO, Map<String, Any?>>,
        updateProfileRequestMapper: IOneSideMapper<UpdatedProfileRequestDTO, Map<String, Any?>>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IProfilesRemoteDataSource = ProfilesRemoteDataSourceImpl(
        firebaseStore,
        profilesMapper,
        createProfileRequestMapper,
        updateProfileRequestMapper,
        dispatcher
    )

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(
        firebaseStore: FirebaseFirestore,
        usersMapper: IOneSideMapper<Map<String, Any?>, UserResponseDTO>,
        updatedUserRequestMapper: IOneSideMapper<UpdatedUserRequestDTO, Map<String, Any?>>,
        createUserRequestMapper: IOneSideMapper<CreateUserDTO, Map<String, Any?>>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IUserRemoteDataSource = UserRemoteDataSourceImpl(
        firebaseStore,
        usersMapper,
        updatedUserRequestMapper,
        createUserRequestMapper,
        dispatcher
    )

    @Provides
    @Singleton
    fun provideFavoritesRemoteDataSource(
        firebaseStore: FirebaseFirestore,
        addFavoriteMapper: IOneSideMapper<AddFavoriteSongDTO, Map<String, Any?>>,
        favoriteMapper: IOneSideMapper<Map<String, Any?>, FavoriteSongDTO>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IFavoritesRemoteDataSource = FavoritesRemoteDataSourceImpl(
        firebaseStore,
        addFavoriteMapper,
        favoriteMapper,
        dispatcher
    )

    @Provides
    @Singleton
    fun provideSubscriptionRemoteDataSource(
        firebaseStore: FirebaseFirestore,
        subscriptionsMapper: IOneSideMapper<Map<String, Any?>, SubscriptionDTO>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): ISubscriptionsRemoteDataSource = SubscriptionsRemoteDataSourceImpl(
        firebaseStore,
        subscriptionsMapper,
        dispatcher
    )

    @Provides
    @Singleton
    fun provideUserSubscriptionsRemoteDataSource(
        firebaseStore: FirebaseFirestore,
        userSubscriptionsMapper: IOneSideMapper<Map<String, Any?>, UserSubscriptionDTO>,
        addSubscriptionMapper: IOneSideMapper<AddUserSubscriptionDTO, Map<String, Any?>>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IUserSubscriptionsRemoteDataSource = UserSubscriptionsRemoteDataSourceImpl(
        firebaseStore,
        userSubscriptionsMapper,
        addSubscriptionMapper,
        dispatcher
    )

    @Provides
    @Singleton
    fun provideArtistsRemoteDataSource(
        firebaseStore: FirebaseFirestore,
        artistMapper: IOneSideMapper<Map<String, Any?>, ArtistDTO>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IArtistsRemoteDataSource = ArtistsRemoteDataSourceImpl(
        firebaseStore,
        artistMapper,
        dispatcher
    )

    @Provides
    @Singleton
    fun provideSongRemoteDataSource(
        firebaseStore: FirebaseFirestore,
        dataMapper: IOneSideMapper<Map<String, Any?>, SongDTO>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): ISongRemoteDataSource = SongRemoteDataSourceImpl(
        firebaseStore,
        dataMapper,
        dispatcher
    )
}