package com.dreamsoftware.melodiqtv.ui.screens.category

import com.dreamsoftware.melodiqtv.domain.model.CategoryBO
import com.dreamsoftware.melodiqtv.domain.usecase.GetCategoryByIdUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.GetSongsByCategoryUseCase
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import com.dreamsoftware.melodiqtv.domain.model.SongBO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryDetailScreenViewModel @Inject constructor(
    private val getSongsByCategoryUseCase: GetSongsByCategoryUseCase,
    private val getCategoryByIdUseCase: GetCategoryByIdUseCase
) : FudgeTvViewModel<CategoryDetailUiState, CategoryDetailSideEffects>(), CategoryDetailActionListener {

    fun fetchData(id: String) {
        fetchCategoryDetail(id)
    }

    override fun onGetDefaultState(): CategoryDetailUiState = CategoryDetailUiState()

    override fun onSongOpened(song: SongBO) {
        with(song) {
            launchSideEffect(
                CategoryDetailSideEffects.OpenSongDetail(id = id)
            )
        }
    }

    private fun fetchSongsByCategory(id: String) {
        executeUseCaseWithParams(
            useCase = getSongsByCategoryUseCase,
            params = GetSongsByCategoryUseCase.Params(id),
            onSuccess = ::onGetSongsByCategorySuccessfully
        )
    }

    private fun fetchCategoryDetail(id: String) {
        executeUseCaseWithParams(
            useCase = getCategoryByIdUseCase,
            params = GetCategoryByIdUseCase.Params(id),
            onSuccess = ::onGetCategoryDetailSuccessfully
        )
    }

    private fun onGetSongsByCategorySuccessfully(songs: List<SongBO>) {
        updateState { it.copy(songs = songs) }
    }

    private fun onGetCategoryDetailSuccessfully(category: CategoryBO) {
        updateState { it.copy(category = category) }
        fetchSongsByCategory(category.id)
    }
}

data class CategoryDetailUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val songs: List<SongBO> = emptyList(),
    val category: CategoryBO? = null
): UiState<CategoryDetailUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): CategoryDetailUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface CategoryDetailSideEffects : SideEffect {
    data class OpenSongDetail(val id: String): CategoryDetailSideEffects
}