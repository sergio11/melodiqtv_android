package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.melodiqtv.domain.model.ClassLanguageEnum
import com.dreamsoftware.melodiqtv.domain.model.ITrainingProgramBO
import com.dreamsoftware.melodiqtv.domain.model.IntensityEnum
import com.dreamsoftware.melodiqtv.domain.model.SortTypeEnum
import com.dreamsoftware.melodiqtv.domain.model.TrainingFilterDataBO
import com.dreamsoftware.melodiqtv.domain.model.TrainingTypeEnum
import com.dreamsoftware.melodiqtv.domain.model.VideoLengthEnum
import com.dreamsoftware.melodiqtv.domain.model.WorkoutTypeEnum
import com.dreamsoftware.melodiqtv.domain.repository.ISubscriptionsRepository
import com.dreamsoftware.melodiqtv.domain.repository.ITrainingRepository
import com.dreamsoftware.melodiqtv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class GetTrainingsByTypeUseCase(
    private val userRepository: IUserRepository,
    private val subscriptionsRepository: ISubscriptionsRepository,
    private val trainingRepository: ITrainingRepository
) : FudgeTvUseCaseWithParams<GetTrainingsByTypeUseCase.Params, List<ITrainingProgramBO>>() {

    override suspend fun onExecuted(params: Params): List<ITrainingProgramBO> {
        val userUid = userRepository.getAuthenticatedUid()
        val hasActiveSubscription = subscriptionsRepository.hasActiveSubscription(userUid)
        return trainingRepository.getTrainings(
            data = params.toTrainingFilterData(),
            includePremium = hasActiveSubscription
        ).toList()
    }

    private fun Params.toTrainingFilterData() = TrainingFilterDataBO(
        type = type,
        classLanguage = classLanguage,
        workoutType = workoutType,
        intensity = intensity,
        videoLength = videoLength,
        sortType = sortType,
        instructor = instructor
    )

    data class Params(
        val type: TrainingTypeEnum,
        val classLanguage: ClassLanguageEnum,
        val workoutType: WorkoutTypeEnum,
        val intensity: IntensityEnum,
        val videoLength: VideoLengthEnum,
        val sortType: SortTypeEnum,
        val instructor: String
    )
}