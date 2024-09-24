package com.dreamsoftware.melodiqtv.ui.screens.songhub

import com.dreamsoftware.melodiqtv.R
import com.dreamsoftware.melodiqtv.domain.model.LanguageEnum
import com.dreamsoftware.melodiqtv.domain.model.ArtistBO
import com.dreamsoftware.melodiqtv.domain.model.SortTypeEnum
import com.dreamsoftware.melodiqtv.domain.model.DurationEnum
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
import com.dreamsoftware.melodiqtv.domain.model.SongGenreEnum
import com.dreamsoftware.melodiqtv.domain.model.SongMoodEnum
import com.dreamsoftware.melodiqtv.domain.model.SongTypeEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SongHubViewModel @Inject constructor(
    private val getArtistsUseCase: GetArtistsUseCase,
    private val getSongsByTypeUseCase: GetSongsByTypeUseCase,
    private val applicationAware: IFudgeTvApplicationAware,
    @SongsScreenErrorMapper private val errorMapper: IFudgeTvErrorMapper,
) : FudgeTvViewModel<SongsUiState, SongsSideEffects>(), SongHubScreenActionListener {

    private var artists: List<ArtistBO> = emptyList()
    private var artistFilter: String = String.EMPTY
    private var songType: SongTypeEnum = SongTypeEnum.ACOUSTIC
    private var duration: DurationEnum = DurationEnum.NOT_SET
    private var genre: SongGenreEnum = SongGenreEnum.NOT_SET
    private var mood: SongMoodEnum = SongMoodEnum.NOT_SET
    private var language: LanguageEnum = LanguageEnum.NOT_SET
    private var sortType: SortTypeEnum = SortTypeEnum.NOT_SET

    override fun onGetDefaultState(): SongsUiState = SongsUiState(
        filterItems = listOf(
            FudgeTvFilterVO(
                id = DURATION_FILTER,
                icon = R.drawable.length_ic,
                title = R.string.song_hub_duration_filter_option_text,
                description = DurationEnum.NOT_SET.value,
                options = DurationEnum.entries.map { it.value }
            ),
            FudgeTvFilterVO(
                id = LANGUAGE_FILTER,
                icon = R.drawable.language_ic,
                title = R.string.song_hub_language_filter_option_text,
                description = LanguageEnum.NOT_SET.value,
                options = LanguageEnum.entries.map { it.value }
            ),
            FudgeTvFilterVO(
                id = GENRE_FILTER,
                icon = R.drawable.ic_music_genre,
                title = R.string.song_hub_genre_filter_option_text,
                description = SongGenreEnum.NOT_SET.value,
                options = SongGenreEnum.entries.map { it.value }
            ),
            FudgeTvFilterVO(
                id = MOOD_FILTER,
                icon = R.drawable.ic_mood_filter,
                title = R.string.song_hub_mood_filter_option_text,
                description = SongMoodEnum.NOT_SET.value,
                options = SongMoodEnum.entries.map { it.value }
            ),
            FudgeTvFilterVO(
                id = ARTIST_FILTER,
                icon = R.drawable.ic_music_artist,
                title = R.string.song_hub_artist_filter_option_text
            )
        )
    )

    fun fetchData() {
        fetchSongs()
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
        fetchSongs()
    }

    override fun onDismissSortSideMenu() {
        updateState { it.copy(isSortExpended = false) }
    }

    override fun onDismissFilterSideMenu() {
        updateState { it.copy(isFilterExpended = false) }
    }

    override fun onFilterCleared() {
        resetFilters()
        fetchSongs()
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
        fetchSongs()
    }

    override fun onSelectedFilterOption(currentIndex: Int) {
        updateState { it.copy(isFieldFilterSelected = false) }
        uiState.value.selectedFilter?.let { filter ->
            when(filter.id) {
                DURATION_FILTER -> {
                    duration = DurationEnum.entries[currentIndex]
                }
                MOOD_FILTER -> {
                    mood = SongMoodEnum.entries[currentIndex]
                }
                GENRE_FILTER -> {
                    genre = SongGenreEnum.entries[currentIndex]
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
                                    DURATION_FILTER -> DurationEnum.entries[currentIndex].value
                                    MOOD_FILTER -> SongMoodEnum.entries[currentIndex].value
                                    GENRE_FILTER -> SongGenreEnum.entries[currentIndex].value
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
            fetchSongs()
        }
    }

    override fun onChangeSelectedTab(index: Int) {
        updateState {
            it.copy(
                selectedTab = index,
                songs = emptyList()
            )
        }

        songType = SongTypeEnum.entries[index]
        fetchSongs()
    }

    override fun onChangeFocusTab(index: Int) {
        updateState { it.copy(focusTabIndex = index) }
    }

    override fun onItemClicked(id: String) {
        launchSideEffect(
            SongsSideEffects.OpenSongDetail(id = id)
        )
    }

    private fun fetchArtists() {
        executeUseCase(useCase = getArtistsUseCase, onSuccess = ::onGetArtistsSuccessfully)
    }

    private fun fetchSongs() {
        executeUseCaseWithParams(
            useCase = getSongsByTypeUseCase,
            params = GetSongsByTypeUseCase.Params(
                type = songType,
                language = language,
                genre = genre,
                mood = mood,
                duration = duration,
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
            fetchArtists()
        }
    }

    private fun onGetArtistsSuccessfully(artists: List<ArtistBO>) {
        this.artists = artists
        val noArtistSet = applicationAware.getString(R.string.song_hub_not_artist_filter_set)
        updateState {
            it.copy(
                filterItems = it.filterItems.map { item ->
                    if(item.id == ARTIST_FILTER) {
                        item.copy(
                            options = artists.map(ArtistBO::name) + noArtistSet,
                            description = noArtistSet
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
        duration = DurationEnum.NOT_SET
        genre = SongGenreEnum.NOT_SET
        mood = SongMoodEnum.NOT_SET
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
                DURATION_FILTER -> DurationEnum.NOT_SET.value
                MOOD_FILTER -> SongMoodEnum.NOT_SET.value
                GENRE_FILTER -> SongGenreEnum.NOT_SET.value
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
        R.string.song_type_acoustic_name,
        R.string.song_type_studio_name,
        R.string.song_type_live_name,
        R.string.song_type_remix_name,
    ),
    val focusTabIndex: Int = 0,
) : UiState<SongsUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): SongsUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface SongsSideEffects : SideEffect {
    data class OpenSongDetail(val id: String) : SongsSideEffects
}

const val DURATION_FILTER = "DURATION_FILTER"
const val MOOD_FILTER = "MOOD_FILTER"
const val GENRE_FILTER = "GENRE_FILTER"
const val LANGUAGE_FILTER = "LANGUAGE_FILTER"
const val ARTIST_FILTER = "ARTIST_FILTER"