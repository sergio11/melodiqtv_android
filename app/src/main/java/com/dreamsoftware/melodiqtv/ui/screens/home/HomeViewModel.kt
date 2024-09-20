package com.dreamsoftware.melodiqtv.ui.screens.home

import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import com.dreamsoftware.melodiqtv.domain.model.CategoryBO
import com.dreamsoftware.melodiqtv.domain.model.SongBO
import com.dreamsoftware.melodiqtv.domain.usecase.GetCategoriesUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.GetFeaturedSongsUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.GetSongsRecommendedUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.HasActiveSubscriptionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFeaturedSongsUseCase: GetFeaturedSongsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getSongsRecommendedUseCase: GetSongsRecommendedUseCase,
    private val hasActiveSubscriptionUseCase: HasActiveSubscriptionUseCase
) : FudgeTvViewModel<HomeUiState, HomeSideEffects>(), HomeScreenActionListener {

    override fun onGetDefaultState(): HomeUiState = HomeUiState()

    fun fetchData() {
        fetchFeaturedSongs()
        fetchCategories()
        fetchSongsRecommended()
        verifyHasActiveSubscription()
    }

    private fun fetchFeaturedSongs() {
        executeUseCase(useCase = getFeaturedSongsUseCase, onSuccess = ::onGetFeaturedSongsSuccessfully)
    }

    private fun fetchCategories() {
        executeUseCase(useCase = getCategoriesUseCase, onSuccess = ::onGetCategoriesSuccessfully)
    }

    private fun fetchSongsRecommended() {
        executeUseCase(useCase = getSongsRecommendedUseCase, onSuccess = ::onGetSongsRecommendedSuccessfully)
    }

    private fun verifyHasActiveSubscription() {
        executeUseCase(useCase = hasActiveSubscriptionUseCase, onSuccess = ::onVerifyHasActiveSubscriptionCompleted)
    }

    private fun onGetFeaturedSongsSuccessfully(songs: List<SongBO>) {
        updateState { it.copy(featuredSongs = songs) }
    }

    private fun onVerifyHasActiveSubscriptionCompleted(hasActiveSubscription: Boolean){
        if(!hasActiveSubscription) {
            launchSideEffect(HomeSideEffects.NoActivePremiumSubscription)
        }
    }

    private fun onGetCategoriesSuccessfully(categories: List<CategoryBO>) {
        updateState { it.copy(categories = categories) }
    }

    private fun onGetSongsRecommendedSuccessfully(songsRecommended: List<SongBO>) {
        updateState { it.copy(recommended = songsRecommended) }
    }

    override fun onOpenSongDetail(song: SongBO) {
        with(song) {
            launchSideEffect(HomeSideEffects.OpenSongDetail(id = id))
        }
    }

    override fun onCategorySelected(categoryId: String) {
        launchSideEffect(HomeSideEffects.OpenSongCategory(categoryId))
    }
}

data class HomeUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val categories: List<CategoryBO> = listOf(),
    val featuredSongs: List<SongBO> = emptyList(),
    val recommended: List<SongBO> = listOf()
): UiState<HomeUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): HomeUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface HomeSideEffects: SideEffect {
    data class OpenSongDetail(val id: String): HomeSideEffects
    data class OpenSongCategory(val categoryId: String): HomeSideEffects
    data object NoActivePremiumSubscription: HomeSideEffects
}