package com.dreamsoftware.melodiqtv.di

import com.dreamsoftware.melodiqtv.domain.model.CreateProfileRequestBO
import com.dreamsoftware.melodiqtv.domain.model.SignInBO
import com.dreamsoftware.melodiqtv.domain.model.SignUpBO
import com.dreamsoftware.melodiqtv.domain.model.UpdatedProfileRequestBO
import com.dreamsoftware.melodiqtv.domain.repository.ICategoryRepository
import com.dreamsoftware.melodiqtv.domain.repository.IInstructorRepository
import com.dreamsoftware.melodiqtv.domain.repository.IProfilesRepository
import com.dreamsoftware.melodiqtv.domain.repository.ISubscriptionsRepository
import com.dreamsoftware.melodiqtv.domain.repository.ITrainingRepository
import com.dreamsoftware.melodiqtv.domain.repository.ITrainingSongsRepository
import com.dreamsoftware.melodiqtv.domain.repository.IUserRepository
import com.dreamsoftware.melodiqtv.domain.usecase.AddFavoriteTrainingUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.AddUserSubscriptionUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.ChangeSecurePinUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.CreateProfileUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.DeleteProfileUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.GetCategoriesUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.GetCategoryByIdUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.GetFavoritesTrainingsByUserUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.GetFeaturedTrainingsUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.GetInstructorDetailUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.GetInstructorsUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.GetProfileByIdUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.GetProfileSelectedUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.GetProfilesUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.GetSongByIdUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.GetSubscriptionsUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.GetTrainingByIdUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.GetTrainingsByCategoryUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.GetTrainingsByTypeUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.GetTrainingsRecommendedUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.GetUserDetailUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.GetUserPreferencesUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.GetUserProfilesUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.GetUserSubscriptionUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.HasActiveSubscriptionUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.HasMultiplesProfilesUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.RemoveFavoriteTrainingUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.RemoveUserSubscriptionUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.SaveUserPreferencesUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.SelectProfileUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.SignInUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.SignOffUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.SignUpUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.UpdateProfileUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.VerifyPinUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.VerifyTrainingInFavoritesUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.VerifyUserSessionUseCase
import com.dreamsoftware.melodiqtv.domain.validation.IBusinessEntityValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideGetUserProfilesUseCase(
        userRepository: IUserRepository,
        profilesRepository: IProfilesRepository
    ): GetUserProfilesUseCase =
        GetUserProfilesUseCase(
            userRepository = userRepository,
            profilesRepository = profilesRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetCategoriesUseCase(
        categoryRepository: ICategoryRepository
    ): GetCategoriesUseCase =
        GetCategoriesUseCase(
            categoryRepository = categoryRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetTrainingsRecommendedUseCase(
        userRepository: IUserRepository,
        subscriptionsRepository: ISubscriptionsRepository,
        trainingRepository: ITrainingRepository
    ): GetTrainingsRecommendedUseCase =
        GetTrainingsRecommendedUseCase(
            userRepository = userRepository,
            subscriptionsRepository = subscriptionsRepository,
            trainingRepository = trainingRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetTrainingByIdUseCase(
        trainingRepository: ITrainingRepository
    ): GetTrainingByIdUseCase =
        GetTrainingByIdUseCase(
            trainingRepository = trainingRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetTrainingsByTypeUseCase(
        userRepository: IUserRepository,
        subscriptionsRepository: ISubscriptionsRepository,
        trainingRepository: ITrainingRepository
    ): GetTrainingsByTypeUseCase =
        GetTrainingsByTypeUseCase(
            userRepository = userRepository,
            subscriptionsRepository = subscriptionsRepository,
            trainingRepository = trainingRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetInstructorsUseCase(
        instructorRepository: IInstructorRepository
    ): GetInstructorsUseCase =
        GetInstructorsUseCase(
            instructorRepository = instructorRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetSongByIdUseCase(
        trainingSongRepository: ITrainingSongsRepository
    ): GetSongByIdUseCase =
        GetSongByIdUseCase(
            trainingSongRepository = trainingSongRepository
        )

    @Provides
    @ViewModelScoped
    fun provideSignInUseCase(
        userRepository: IUserRepository,
        validator: IBusinessEntityValidator<SignInBO>
    ): SignInUseCase =
        SignInUseCase(
            userRepository = userRepository,
            validator = validator
        )

    @Provides
    @ViewModelScoped
    fun provideSignUpUseCase(
        userRepository: IUserRepository,
        profilesRepository: IProfilesRepository,
        validator: IBusinessEntityValidator<SignUpBO>
    ): SignUpUseCase =
        SignUpUseCase(
            userRepository = userRepository,
            profilesRepository = profilesRepository,
            validator = validator
        )


    @Provides
    @ViewModelScoped
    fun provideGetFeaturedTrainingsUseCase(
        userRepository: IUserRepository,
        subscriptionsRepository: ISubscriptionsRepository,
        trainingRepository: ITrainingRepository
    ): GetFeaturedTrainingsUseCase =
        GetFeaturedTrainingsUseCase(
            userRepository = userRepository,
            subscriptionsRepository = subscriptionsRepository,
            trainingRepository = trainingRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetTrainingsByCategoryUseCase(
        userRepository: IUserRepository,
        subscriptionsRepository: ISubscriptionsRepository,
        trainingRepository: ITrainingRepository
    ): GetTrainingsByCategoryUseCase =
        GetTrainingsByCategoryUseCase(
            userRepository = userRepository,
            subscriptionsRepository = subscriptionsRepository,
            trainingRepository = trainingRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetCategoryByIdUseCase(
        categoryRepository: ICategoryRepository
    ): GetCategoryByIdUseCase =
        GetCategoryByIdUseCase(
            categoryRepository = categoryRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetUserDetailUseCase(
        userRepository: IUserRepository
    ): GetUserDetailUseCase =
        GetUserDetailUseCase(
            userRepository = userRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetProfilesUseCase(
        userRepository: IUserRepository,
        profilesRepository: IProfilesRepository
    ): GetProfilesUseCase =
        GetProfilesUseCase(
            userRepository = userRepository,
            profilesRepository = profilesRepository
        )


    @Provides
    @ViewModelScoped
    fun provideSelectProfileUseCase(
        profilesRepository: IProfilesRepository
    ): SelectProfileUseCase =
        SelectProfileUseCase(
            profilesRepository = profilesRepository
        )


    @Provides
    @ViewModelScoped
    fun provideGetProfileSelectedUseCase(
        userRepository: IUserRepository,
        profilesRepository: IProfilesRepository
    ): GetProfileSelectedUseCase =
        GetProfileSelectedUseCase(
            userRepository = userRepository,
            profilesRepository = profilesRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetProfileByIdUseCase(
        profilesRepository: IProfilesRepository
    ): GetProfileByIdUseCase =
        GetProfileByIdUseCase(
            profilesRepository = profilesRepository
        )

    @Provides
    @ViewModelScoped
    fun provideSignOffUseCase(
        userRepository: IUserRepository
    ): SignOffUseCase =
        SignOffUseCase(
            userRepository = userRepository
        )

    @Provides
    @ViewModelScoped
    fun provideVerifyPinUseCase(
        profilesRepository: IProfilesRepository
    ): VerifyPinUseCase =
        VerifyPinUseCase(
            profilesRepository = profilesRepository
        )

    @Provides
    @ViewModelScoped
    fun provideVerifyUserSessionUseCase(
        userRepository: IUserRepository
    ): VerifyUserSessionUseCase =
        VerifyUserSessionUseCase(
            userRepository = userRepository
        )

    @Provides
    @ViewModelScoped
    fun provideCreateProfileUseCase(
        userRepository: IUserRepository,
        profilesRepository: IProfilesRepository,
        validator: IBusinessEntityValidator<CreateProfileRequestBO>
    ): CreateProfileUseCase =
        CreateProfileUseCase(
            userRepository = userRepository,
            profilesRepository = profilesRepository,
            validator = validator
        )


    @Provides
    @ViewModelScoped
    fun provideUpdateProfileUseCase(
        profilesRepository: IProfilesRepository,
        validator:  IBusinessEntityValidator<UpdatedProfileRequestBO>
    ): UpdateProfileUseCase =
        UpdateProfileUseCase(
            profilesRepository = profilesRepository,
            validator = validator
        )

    @Provides
    @ViewModelScoped
    fun provideAddFavoriteTrainingUseCase(
        userRepository: IUserRepository,
        profileRepository: IProfilesRepository,
        trainingRepository: ITrainingRepository
    ): AddFavoriteTrainingUseCase =
        AddFavoriteTrainingUseCase(
            userRepository = userRepository,
            profileRepository = profileRepository,
            trainingRepository = trainingRepository
        )


    @Provides
    @ViewModelScoped
    fun provideGetFavoritesTrainingsByUserUseCase(
        userRepository: IUserRepository,
        profileRepository: IProfilesRepository,
        trainingRepository: ITrainingRepository
    ): GetFavoritesTrainingsByUserUseCase =
        GetFavoritesTrainingsByUserUseCase(
            userRepository = userRepository,
            profileRepository = profileRepository,
            trainingRepository = trainingRepository
        )

    @Provides
    @ViewModelScoped
    fun provideRemoveFavoriteTrainingUseCase(
        userRepository: IUserRepository,
        trainingRepository: ITrainingRepository
    ): RemoveFavoriteTrainingUseCase =
        RemoveFavoriteTrainingUseCase(
            userRepository = userRepository,
            trainingRepository = trainingRepository
        )


    @Provides
    @ViewModelScoped
    fun provideVerifyTrainingInFavoritesUseCase(
        userRepository: IUserRepository,
        profileRepository: IProfilesRepository,
        trainingRepository: ITrainingRepository
    ): VerifyTrainingInFavoritesUseCase =
        VerifyTrainingInFavoritesUseCase(
            userRepository = userRepository,
            profileRepository = profileRepository,
            trainingRepository = trainingRepository
        )

    @Provides
    @ViewModelScoped
    fun provideDeleteProfileUseCase(
        profileRepository: IProfilesRepository
    ): DeleteProfileUseCase =
        DeleteProfileUseCase(profileRepository = profileRepository)

    @Provides
    @ViewModelScoped
    fun provideHasMultiplesProfilesUseCase(
        userRepository: IUserRepository,
        profilesRepository: IProfilesRepository
    ): HasMultiplesProfilesUseCase =
        HasMultiplesProfilesUseCase(
            userRepository = userRepository,
            profilesRepository = profilesRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetSubscriptionsUseCase(
        subscriptionsRepository: ISubscriptionsRepository
    ): GetSubscriptionsUseCase =
        GetSubscriptionsUseCase(
            subscriptionsRepository = subscriptionsRepository
        )

    @Provides
    @ViewModelScoped
    fun provideHasActiveSubscriptionUseCase(
        userRepository: IUserRepository,
        subscriptionsRepository: ISubscriptionsRepository
    ): HasActiveSubscriptionUseCase =
        HasActiveSubscriptionUseCase(
            userRepository = userRepository,
            subscriptionsRepository = subscriptionsRepository
        )

    @Provides
    @ViewModelScoped
    fun provideRemoveUserSubscriptionUseCase(
        userRepository: IUserRepository,
        subscriptionsRepository: ISubscriptionsRepository
    ): RemoveUserSubscriptionUseCase =
        RemoveUserSubscriptionUseCase(
            userRepository = userRepository,
            subscriptionsRepository = subscriptionsRepository
        )


    @Provides
    @ViewModelScoped
    fun provideAddUserSubscriptionUseCase(
        userRepository: IUserRepository,
        subscriptionsRepository: ISubscriptionsRepository
    ): AddUserSubscriptionUseCase =
        AddUserSubscriptionUseCase(
            userRepository = userRepository,
            subscriptionsRepository = subscriptionsRepository
        )


    @Provides
    @ViewModelScoped
    fun provideGetUserSubscriptionUseCase(
        userRepository: IUserRepository,
        subscriptionsRepository: ISubscriptionsRepository
    ): GetUserSubscriptionUseCase =
        GetUserSubscriptionUseCase(
            userRepository = userRepository,
            subscriptionsRepository = subscriptionsRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetUserPreferencesUseCase(
        userRepository: IUserRepository
    ): GetUserPreferencesUseCase =
        GetUserPreferencesUseCase(
            userRepository = userRepository
        )

    @Provides
    @ViewModelScoped
    fun provideSaveUserPreferencesUseCase(
        userRepository: IUserRepository
    ): SaveUserPreferencesUseCase =
        SaveUserPreferencesUseCase(
            userRepository = userRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetInstructorDetailUseCase(
        instructorRepository: IInstructorRepository
    ): GetInstructorDetailUseCase =
        GetInstructorDetailUseCase(
            instructorRepository = instructorRepository
        )

    @Provides
    @ViewModelScoped
    fun provideChangeSecurePinUseCase(
        profilesRepository: IProfilesRepository,
        validator:  IBusinessEntityValidator<UpdatedProfileRequestBO>
    ): ChangeSecurePinUseCase =
        ChangeSecurePinUseCase(
            profilesRepository = profilesRepository,
            validator = validator
        )
}