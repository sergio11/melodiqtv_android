package com.dreamsoftware.melodiqtv.ui.screens.profiles.secure

import com.dreamsoftware.melodiqtv.domain.model.ProfileBO
import com.dreamsoftware.melodiqtv.domain.usecase.GetProfileByIdUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.SelectProfileUseCase
import com.dreamsoftware.melodiqtv.domain.usecase.VerifyPinUseCase
import com.dreamsoftware.melodiqtv.ui.utils.EMPTY
import com.dreamsoftware.melodiqtv.utils.combinedLet
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SecurePinViewModel @Inject constructor(
    private val verifyPinUseCase: VerifyPinUseCase,
    private val selectProfileUseCase: SelectProfileUseCase,
    private val getProfileByIdUseCase: GetProfileByIdUseCase
): FudgeTvViewModel<SecurePinUiState, SecurePinSideEffects>(), SecurePinScreenActionListener {

    override fun onGetDefaultState(): SecurePinUiState = SecurePinUiState()

    fun load(profileId: String) {
        executeUseCaseWithParams(
            useCase = getProfileByIdUseCase,
            params = GetProfileByIdUseCase.Params(profileId),
            onSuccess = ::onLoadProfileCompleted
        )
    }

    override fun onUnlockPinChanged(unlockPin: String) {
        updateState {
            it.copy(unlockPin = unlockPin)
        }
    }

    override fun onVerifyPressed() {
        with(uiState.value) {
            combinedLet(profileLocked, unlockPin.toIntOrNull()) { profile, pin ->
                executeUseCaseWithParams(
                    useCase = verifyPinUseCase,
                    params = VerifyPinUseCase.Params(profileId = profile.uuid, pin = pin),
                    onSuccess = {
                        onVerifyPinSuccessfully(profile)
                    }
                )
            }
        }
    }

    override fun onCancelPressed() {
        launchSideEffect(SecurePinSideEffects.CancelVerification)
    }

    private fun onLoadProfileCompleted(profileBO: ProfileBO) {
        updateState {
            it.copy(profileLocked = profileBO)
        }
    }

    private fun onVerifyPinSuccessfully(profile: ProfileBO) {
        executeUseCaseWithParams(
            useCase = selectProfileUseCase,
            params = SelectProfileUseCase.Params(profile),
            onSuccess = {
                onProfileSelected()
            }
        )
    }

    private fun onProfileSelected() {
        launchSideEffect(SecurePinSideEffects.ProfileUnlockedSuccessfully)
    }
}

data class SecurePinUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val unlockPin: String = String.EMPTY,
    val profileLocked: ProfileBO? = null
): UiState<SecurePinUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): SecurePinUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface SecurePinSideEffects: SideEffect {
    data object ProfileUnlockedSuccessfully: SecurePinSideEffects
    data object CancelVerification: SecurePinSideEffects
}