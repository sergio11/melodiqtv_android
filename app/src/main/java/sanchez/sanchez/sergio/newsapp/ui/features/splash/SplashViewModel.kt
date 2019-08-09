package sanchez.sanchez.sergio.newsapp.ui.features.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sanchez.sanchez.sergio.brownie.ui.core.viewmodel.SupportViewModel
import sanchez.sanchez.sergio.newsapp.domain.model.User
import javax.inject.Inject

class SplashViewModel @Inject constructor(
): SupportViewModel() {

    val result: MutableLiveData<SplashOperationResultEnum> by lazy {
        MutableLiveData<SplashOperationResultEnum>()
    }

    val user: MutableLiveData<User> by lazy {
        MutableLiveData<User>()
    }

    fun loadUser() = viewModelScope.launch {
        try {
            //user.postValue(userRepository.getUser())
            result.postValue(SplashOperationResultEnum.USER_LOADED)
        } catch (ex: Exception) {
            result.postValue(SplashOperationResultEnum.USER_NOT_AVAILABLE)
        }
    }

}