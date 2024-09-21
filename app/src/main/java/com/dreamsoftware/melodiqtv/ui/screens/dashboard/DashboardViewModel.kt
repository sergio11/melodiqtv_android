package com.dreamsoftware.melodiqtv.ui.screens.dashboard

import com.dreamsoftware.melodiqtv.R
import com.dreamsoftware.melodiqtv.domain.model.AvatarTypeEnum
import com.dreamsoftware.melodiqtv.domain.model.ProfileBO
import com.dreamsoftware.melodiqtv.domain.usecase.GetProfileSelectedUseCase
import com.dreamsoftware.melodiqtv.ui.navigation.Screen
import com.dreamsoftware.melodiqtv.ui.utils.toDrawableResource
import com.dreamsoftware.fudge.component.FudgeTvNavigationDrawerItemModel
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getProfileSelectedUseCase: GetProfileSelectedUseCase
) : FudgeTvViewModel<DashboardUiState, DashboardSideEffects>(), DashboardActionListener {

    fun fetchData() {
        executeUseCase(
            useCase = getProfileSelectedUseCase,
            onSuccess = ::onGetSelectedProfileSuccessfully
        )
    }

    override fun onGetDefaultState(): DashboardUiState = DashboardUiState(
        items = onBuildNavigationDrawerMenuItems()
    )

    override fun onMenuItemSelected(menuItem: FudgeTvNavigationDrawerItemModel) {
        launchSideEffect(DashboardSideEffects.OpenScreen(menuItem.route))
    }

    private fun onGetSelectedProfileSuccessfully(profileBO: ProfileBO) {
        updateState { it.copy(items = onBuildNavigationDrawerMenuItems(profileBO)) }
    }

    private fun onBuildNavigationDrawerMenuItems(currentProfile: ProfileBO? = null) = listOf(
        FudgeTvNavigationDrawerItemModel(
            nameRes = if(currentProfile == null) {
                R.string.dashboard_navigation_drawer_profile_item_name
            } else {
                null
            },
            name = currentProfile?.alias,
            imageRes = (currentProfile?.avatarType ?: AvatarTypeEnum.BOY).toDrawableResource(),
            route = Screen.Profiles.route,
            isIcon = false
        ),
        FudgeTvNavigationDrawerItemModel(
            nameRes = R.string.dashboard_navigation_drawer_home_item_name,
            imageRes = R.drawable.home,
            route = Screen.Home.route
        ),
        FudgeTvNavigationDrawerItemModel(
            nameRes = R.string.dashboard_navigation_drawer_song_directory_item_name,
            imageRes = R.drawable.ic_song_directory,
            route = Screen.Songs.route
        ),
        FudgeTvNavigationDrawerItemModel(
            nameRes = R.string.dashboard_navigation_drawer_favorite_item_name,
            imageRes = R.drawable.favorite,
            route = Screen.Favorite.route
        ),
        FudgeTvNavigationDrawerItemModel(
            nameRes = R.string.dashboard_navigation_drawer_settings_item_name,
            imageRes = R.drawable.settings,
            route = Screen.Settings.route
        )
    )

}

data class DashboardUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val items: List<FudgeTvNavigationDrawerItemModel> = emptyList(),
): UiState<DashboardUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): DashboardUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface DashboardSideEffects: SideEffect {
    data class OpenScreen(val route: String): DashboardSideEffects
}