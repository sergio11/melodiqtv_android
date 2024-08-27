package com.dreamsoftware.melodiqtv.ui.screens.profiles.management

import com.dreamsoftware.melodiqtv.domain.model.ProfileBO
import com.dreamsoftware.melodiqtv.domain.usecase.GetProfilesUseCase
import com.dreamsoftware.melodiqtv.ui.utils.toDrawableResource
import com.dreamsoftware.fudge.component.profiles.ProfileSelectorVO
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfilesManagementViewModel @Inject constructor(
    private val getProfilesUseCase: GetProfilesUseCase,
): FudgeTvViewModel<ProfilesManagementUiState, ProfilesManagementSideEffects>() {

    override fun onGetDefaultState(): ProfilesManagementUiState = ProfilesManagementUiState()

    fun loadProfiles() {
        executeUseCase(
            useCase = getProfilesUseCase,
            onSuccess = ::onLoadProfileSuccessfully
        )
    }

    private fun onLoadProfileSuccessfully(profiles: List<ProfileBO>) {
        updateState {
            it.copy(profiles = profiles.map { profile ->
                ProfileSelectorVO(
                    uuid = profile.uuid,
                    alias = profile.alias,
                    avatarIconRes = profile.avatarType.toDrawableResource()
                )
            })
        }
    }
}

data class ProfilesManagementUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val profiles: List<ProfileSelectorVO> = emptyList(),
): UiState<ProfilesManagementUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): ProfilesManagementUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface ProfilesManagementSideEffects: SideEffect