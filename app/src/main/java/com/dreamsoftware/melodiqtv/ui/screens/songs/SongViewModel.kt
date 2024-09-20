package com.dreamsoftware.melodiqtv.ui.screens.songs

import com.dreamsoftware.melodiqtv.R
import com.dreamsoftware.melodiqtv.domain.model.LanguageEnum
import com.dreamsoftware.melodiqtv.domain.model.ArtistBO
import com.dreamsoftware.melodiqtv.domain.model.IntensityEnum
import com.dreamsoftware.melodiqtv.domain.model.SortTypeEnum
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
import com.dreamsoftware.melodiqtv.di.SongsScreenErrorMapper
import com.dreamsoftware.melodiqtv.domain.model.SongBO
import com.dreamsoftware.melodiqtv.domain.model.SongTypeEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SongViewModel @Inject constructor(
    private val getArtistsUseCase: GetArtistsUseCase,
    private val getSongsByTypeUseCase: GetSongsByTypeUseCase,
    private val applicationAware: IFudgeTvApplicationAware,
    @SongsScreenErrorMapper private val errorMapper: IFudgeTvErrorMapper,
) : FudgeTvViewModel<SongsUiState, SongsSideEffects>(), SongScreenActionListener {

    private var artists: List<ArtistBO> = emptyList()
    private var artistFilter: String = String.EMPTY
    private var songType: SongTypeEnum = SongTypeEnum.ACOUSTIC
    private var videoLength: VideoLengthEnum = VideoLengthEnum.NOT_SET
    private var intensity: IntensityEnum = IntensityEnum.NOT_SET
    private var language: LanguageEnum = LanguageEnum.NOT_SET
    private var sortType: SortTypeEnum = SortTypeEnum.NOT_SET

    override fun onGetDefaultState(): SongsUiState = SongsUiState(
        filterItems = listOf(
            FudgeTvFilterVO(
                id = VIDEO_LENGTH_FILTER,
                icon = R.drawable.length_ic,
                title = R.string.length,
                description = VideoLengthEnum.NOT_SET.value,
                options = VideoLengthEnum.entries.map { it.value }
            ),
            FudgeTvFilterVO(
                id = LANGUAGE_FILTER,
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
                id = ARTIST_FILTER,
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

    override fun onFilterFieldSelected(filter: FudgeTvFilterVO) {
        updateState {
            it.copy(
                selectedFilter = filter,
                isFieldFilterSelected = true
            )
        }
    }

    override fun onSelectedSortedItem(currentIndex: Int) {
        sortType = SortTypeEnum.entries[currentIndex]
        updateState { it.copy(selectedSortItem = currentIndex, isSortExpended = false) }
        fetchTrainings()
    }

    override fun onSelectedFilterOption(currentIndex: Int) {
        updateState { it.copy(isFieldFilterSelected = false) }
        uiState.value.selectedFilter?.let { filter ->
            when(filter.id) {
                VIDEO_LENGTH_FILTER -> {
                    videoLength = VideoLengthEnum.entries[currentIndex]
                }
                DIFFICULTY_FILTER -> {
                    intensity = IntensityEnum.entries[currentIndex]
                }
                LANGUAGE_FILTER -> {
                    language = LanguageEnum.entries[currentIndex]
                }
                ARTIST_FILTER -> {
                    artistFilter = artists.getOrNull(currentIndex)?.id.orEmpty()
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
                                    DIFFICULTY_FILTER -> IntensityEnum.entries[currentIndex].value
                                    LANGUAGE_FILTER -> LanguageEnum.entries[currentIndex].value
                                    else -> artists.getOrNull(currentIndex)?.name.orEmpty()
                                }
                            )
                        } else {
                            item
                        }
                    },
                    selectedFilter = null
                )
            }
            fetchTrainings()
        }
    }

    override fun onChangeSelectedTab(index: Int) {
        updateState {
            it.copy(
                selectedTab = index,
                songs = emptyList()
            )
        }
        fetchTrainings()
    }

    override fun onChangeFocusTab(index: Int) {
        updateState { it.copy(focusTabIndex = index) }
    }

    override fun onItemClicked(id: String) {
        launchSideEffect(
            SongsSideEffects.OpenSongDetail(id = id)
        )
    }

    private fun fetchInstructors() {
        executeUseCase(useCase = getArtistsUseCase, onSuccess = ::onGetInstructorsSuccessfully)
    }

    private fun fetchTrainings() {
        executeUseCaseWithParams(
            useCase = getSongsByTypeUseCase,
            params = GetSongsByTypeUseCase.Params(
                type = songType,
                language = language,
                intensity = intensity,
                videoLength = videoLength,
                sortType = sortType,
                artist = artistFilter
            ),
            onSuccess = ::onGetSongsSuccessfully,
            onMapExceptionToState = ::onMapExceptionToState
        )
    }

    private fun onGetSongsSuccessfully(songs: List<SongBO>) {
        updateState { it.copy(songs = songs) }
        if(artists.isEmpty()) {
            fetchInstructors()
        }
    }

    private fun onGetInstructorsSuccessfully(instructorList: List<ArtistBO>) {
        artists = instructorList
        val noInstructorSet = applicationAware.getString(R.string.no_instructor_set)
        updateState {
            it.copy(
                filterItems = it.filterItems.map { item ->
                    if(item.id == ARTIST_FILTER) {
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

    private fun onMapExceptionToState(ex: Exception, uiState: SongsUiState) =
        uiState.copy(
            isLoading = false,
            songs = emptyList(),
            errorMessage = errorMapper.mapToMessage(ex)
        )

    private fun resetFilters() {
        videoLength = VideoLengthEnum.NOT_SET
        intensity = IntensityEnum.NOT_SET
        songType = SongTypeEnum.ACOUSTIC
        language = LanguageEnum.NOT_SET
        artistFilter = String.EMPTY
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
                DIFFICULTY_FILTER -> IntensityEnum.NOT_SET.value
                LANGUAGE_FILTER -> LanguageEnum.NOT_SET.value
                else -> String.EMPTY
            }
        )
    }
}

data class SongsUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val isFilterExpended: Boolean = false,
    val isFieldFilterSelected: Boolean = false,
    val isSortExpended: Boolean = false,
    val songs: List<SongBO> = emptyList(),
    val filterItems: List<FudgeTvFilterVO> = emptyList(),
    val selectedSortItem: Int = 0,
    val selectedFilter: FudgeTvFilterVO? = null,
    val selectedTab: Int = 0,
    val tabsTitle: List<Int> = listOf(
        R.string.training_type_workout_name,
        R.string.training_type_series_name,
        R.string.training_type_challenges_name,
        R.string.training_type_routines_name,
    ),
    val focusTabIndex: Int = 0,
) : UiState<SongsUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): SongsUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface SongsSideEffects : SideEffect {
    data class OpenSongDetail(val id: String) : SongsSideEffects
}

const val VIDEO_LENGTH_FILTER = "VIDEO_LENGTH_FILTER"
const val CLASS_TYPE_FILTER = "CLASS_TYPE_FILTER"
const val DIFFICULTY_FILTER = "DIFFICULTY_FILTER"
const val LANGUAGE_FILTER = "LANGUAGE_FILTER"
const val ARTIST_FILTER = "ARTIST_FILTER"