package com.dreamsoftware.melodiqtv.ui.screens.training

import com.dreamsoftware.melodiqtv.R
import com.dreamsoftware.melodiqtv.di.FavoritesScreenErrorMapper
import com.dreamsoftware.melodiqtv.domain.model.LanguageEnum
import com.dreamsoftware.melodiqtv.domain.model.ArtistBO
import com.dreamsoftware.melodiqtv.domain.model.IntensityEnum
import com.dreamsoftware.melodiqtv.domain.model.SortTypeEnum
import com.dreamsoftware.melodiqtv.domain.model.TrainingTypeEnum
import com.dreamsoftware.melodiqtv.domain.model.VideoLengthEnum
import com.dreamsoftware.melodiqtv.domain.usecase.GetArtistsUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.GetSongsByTypeUseCase
import com.dreamsoftware.melodiqtv.ui.utils.EMPTY
import com.dreamsoftware.fudge.component.menu.FudgeTvFilterVO
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.IFudgeTvErrorMapper
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import com.dreamsoftware.fudge.utils.IFudgeTvApplicationAware
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrainingViewModel @Inject constructor(
    private val getArtistsUseCase: GetArtistsUseCase,
    private val getSongsByTypeUseCase: GetSongsByTypeUseCase,
    private val applicationAware: IFudgeTvApplicationAware,
    @FavoritesScreenErrorMapper private val errorMapper: IFudgeTvErrorMapper,
) : FudgeTvViewModel<TrainingUiState, TrainingSideEffects>(), TrainingScreenActionListener {

    private var instructors: List<ArtistBO> = emptyList()
    private var instructor: String = String.EMPTY
    private var videoLength: VideoLengthEnum = VideoLengthEnum.NOT_SET
    private var workoutType: WorkoutTypeEnum = WorkoutTypeEnum.NOT_SET
    private var intensity: IntensityEnum = IntensityEnum.NOT_SET
    private var classLanguage: LanguageEnum = LanguageEnum.NOT_SET
    private var sortType: SortTypeEnum = SortTypeEnum.NOT_SET

    override fun onGetDefaultState(): TrainingUiState = TrainingUiState(
        filterItems = listOf(
            FudgeTvFilterVO(
                id = VIDEO_LENGTH_FILTER,
                icon = R.drawable.length_ic,
                title = R.string.length,
                description = VideoLengthEnum.NOT_SET.value,
                options = VideoLengthEnum.entries.map { it.value }
            ),
            FudgeTvFilterVO(
                id = CLASS_TYPE_FILTER,
                icon = R.drawable.class_type_ic,
                title = R.string.class_type,
                description = WorkoutTypeEnum.NOT_SET.value,
                options = WorkoutTypeEnum.entries.map { it.value }
            ),
            FudgeTvFilterVO(
                id = CLASS_LANGUAGE_FILTER,
                icon = R.drawable.language_ic,
                title = R.string.class_language,
                description = LanguageEnum.NOT_SET.value,
                options = LanguageEnum.entries.map { it.value }
            ),
            FudgeTvFilterVO(
                id = DIFFICULTY_FILTER,
                icon = R.drawable.difficulty_ic,
                title = R.string.difficulty,
                description = IntensityEnum.NOT_SET.value,
                options = IntensityEnum.entries.map { it.value }
            ),
            FudgeTvFilterVO(
                id = INSTRUCTOR_FILTER,
                icon = R.drawable.person_ic,
                title = R.string.instructor
            )
        )
    )

    fun fetchData() {
        fetchTrainings()
    }

    override fun onFilterClicked() {
        updateState { it.copy(isFilterExpended = !it.isFilterExpended) }
    }

    override fun onSortedClicked() {
        updateState { it.copy(isSortExpended = !it.isSortExpended) }
    }

    override fun onSortCleared() {
        sortType = SortTypeEnum.NOT_SET
        updateState { it.copy(selectedSortItem = 0, isSortExpended = false) }
        fetchTrainings()
    }

    override fun onDismissSortSideMenu() {
        updateState { it.copy(isSortExpended = false) }
    }

    override fun onDismissFilterSideMenu() {
        updateState { it.copy(isFilterExpended = false) }
    }

    override fun onFilterCleared() {
        resetFilters()
        fetchTrainings()
    }

    override fun onDismissFieldFilterSideMenu() {
        updateState { it.copy(isFieldFilterSelected = false) }
    }

    override fun onFilterFieldSelected(trainingFilter: FudgeTvFilterVO) {
        updateState {
            it.copy(
                selectedTrainingFilter = trainingFilter,
                isFieldFilterSelected = true
            )
        }
    }

    override fun onSelectedSortedItem(currentIndex: Int) {
        sortType = SortTypeEnum.entries[currentIndex]
        updateState { it.copy(selectedSortItem = currentIndex, isSortExpended = false) }
        fetchTrainings()
    }

    override fun onSelectedTrainingFilterOption(currentIndex: Int) {
        updateState { it.copy(isFieldFilterSelected = false) }
        uiState.value.selectedTrainingFilter?.let { filter ->
            when(filter.id) {
                VIDEO_LENGTH_FILTER -> {
                    videoLength = VideoLengthEnum.entries[currentIndex]
                }
                CLASS_TYPE_FILTER -> {
                    workoutType = WorkoutTypeEnum.entries[currentIndex]
                }
                DIFFICULTY_FILTER -> {
                    intensity = IntensityEnum.entries[currentIndex]
                }
                CLASS_LANGUAGE_FILTER -> {
                    classLanguage = LanguageEnum.entries[currentIndex]
                }
                INSTRUCTOR_FILTER -> {
                    instructor = instructors.getOrNull(currentIndex)?.id.orEmpty()
                }
            }
            updateState {
                it.copy(
                    filterItems = it.filterItems.map { item ->
                        if(item.id == filter.id) {
                            item.copy(
                                selectedOption = currentIndex,
                                description = when(filter.id) {
                                    VIDEO_LENGTH_FILTER -> VideoLengthEnum.entries[currentIndex].value
                                    CLASS_TYPE_FILTER -> WorkoutTypeEnum.entries[currentIndex].value
                                    DIFFICULTY_FILTER -> IntensityEnum.entries[currentIndex].value
                                    CLASS_LANGUAGE_FILTER -> LanguageEnum.entries[currentIndex].value
                                    else -> instructors.getOrNull(currentIndex)?.name.orEmpty()
                                }
                            )
                        } else {
                            item
                        }
                    },
                    selectedTrainingFilter = null
                )
            }
            fetchTrainings()
        }
    }

    override fun onChangeSelectedTab(index: Int) {
        updateState {
            it.copy(
                selectedTab = index,
                trainingPrograms = emptyList(),
                trainingTypeSelected = TrainingTypeEnum.entries[index]
            )
        }
        fetchTrainings()
    }

    override fun onChangeFocusTab(index: Int) {
        updateState { it.copy(focusTabIndex = index) }
    }

    override fun onItemClicked(id: String) {
        launchSideEffect(
            TrainingSideEffects.OpenTrainingProgramDetail(
                id = id,
                type = uiState.value.trainingTypeSelected
            )
        )
    }

    private fun fetchInstructors() {
        executeUseCase(useCase = getArtistsUseCase, onSuccess = ::onGetInstructorsSuccessfully)
    }

    private fun fetchTrainings() {
        executeUseCaseWithParams(
            useCase = getSongsByTypeUseCase,
            params = GetSongsByTypeUseCase.Params(
                type = uiState.value.trainingTypeSelected,
                language = classLanguage,
                workoutType = workoutType,
                intensity = intensity,
                videoLength = videoLength,
                sortType = sortType,
                artist = instructor
            ),
            onSuccess = ::onGetTrainingProgramsSuccessfully,
            onMapExceptionToState = ::onMapExceptionToState
        )
    }

    private fun onGetTrainingProgramsSuccessfully(trainingPrograms: List<ITrainingProgramBO>) {
        updateState { it.copy(trainingPrograms = trainingPrograms) }
        if(instructors.isEmpty()) {
            fetchInstructors()
        }
    }

    private fun onGetInstructorsSuccessfully(instructorList: List<ArtistBO>) {
        instructors = instructorList
        val noInstructorSet = applicationAware.getString(R.string.no_instructor_set)
        updateState {
            it.copy(
                filterItems = it.filterItems.map { item ->
                    if(item.id == INSTRUCTOR_FILTER) {
                        item.copy(
                            options = instructorList.map(ArtistBO::name) + noInstructorSet,
                            description = noInstructorSet
                        )
                    } else {
                        item
                    }
                }
            )
        }
    }

    private fun onMapExceptionToState(ex: Exception, uiState: TrainingUiState) =
        uiState.copy(
            isLoading = false,
            trainingPrograms = emptyList(),
            errorMessage = errorMapper.mapToMessage(ex)
        )

    private fun resetFilters() {
        videoLength = VideoLengthEnum.NOT_SET
        workoutType = WorkoutTypeEnum.NOT_SET
        intensity = IntensityEnum.NOT_SET
        classLanguage = LanguageEnum.NOT_SET
        instructor = String.EMPTY
        updateState {
            it.copy(
                isFilterExpended = false,
                filterItems = it.filterItems.resetOptions()
            )
        }
    }

    private fun List<FudgeTvFilterVO>.resetOptions() = map { item ->
        item.copy(
            selectedOption = 0,
            description = when(item.id) {
                VIDEO_LENGTH_FILTER -> VideoLengthEnum.NOT_SET.value
                CLASS_TYPE_FILTER -> WorkoutTypeEnum.NOT_SET.value
                DIFFICULTY_FILTER -> IntensityEnum.NOT_SET.value
                CLASS_LANGUAGE_FILTER -> LanguageEnum.NOT_SET.value
                else -> String.EMPTY
            }
        )
    }
}

data class TrainingUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val isFilterExpended: Boolean = false,
    val isFieldFilterSelected: Boolean = false,
    val isSortExpended: Boolean = false,
    val trainingPrograms: List<ITrainingProgramBO> = emptyList(),
    val filterItems: List<FudgeTvFilterVO> = emptyList(),
    val selectedSortItem: Int = 0,
    val selectedTrainingFilter: FudgeTvFilterVO? = null,
    val selectedTab: Int = 0,
    val tabsTitle: List<Int> = listOf(
        R.string.training_type_workout_name,
        R.string.training_type_series_name,
        R.string.training_type_challenges_name,
        R.string.training_type_routines_name,
    ),
    val trainingTypeSelected: TrainingTypeEnum = TrainingTypeEnum.WORK_OUT,
    val focusTabIndex: Int = 0,
) : UiState<TrainingUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): TrainingUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface TrainingSideEffects : SideEffect {
    data class OpenTrainingProgramDetail(val id: String, val type: TrainingTypeEnum) :
        TrainingSideEffects
}

const val VIDEO_LENGTH_FILTER = "VIDEO_LENGTH_FILTER"
const val CLASS_TYPE_FILTER = "CLASS_TYPE_FILTER"
const val DIFFICULTY_FILTER = "DIFFICULTY_FILTER"
const val CLASS_LANGUAGE_FILTER = "CLASS_LANGUAGE_FILTER"
const val INSTRUCTOR_FILTER = "INSTRUCTOR_FILTER"