package sanchez.sanchez.sergio.newsapp.ui.features.splash

import android.os.Bundle
import androidx.lifecycle.Observer
import sanchez.sanchez.sergio.brownie.ui.core.activity.SupportActivity
import sanchez.sanchez.sergio.brownie.ui.core.fragment.SupportFragment
import sanchez.sanchez.sergio.healthycitizen.R
import sanchez.sanchez.sergio.newsapp.di.components.fragment.SplashScreenComponent
import sanchez.sanchez.sergio.newsapp.di.factory.DaggerComponentFactory
import sanchez.sanchez.sergio.newsapp.domain.model.User

class SplashScreenFragment : SupportFragment<SplashViewModel, Void>(SplashViewModel::class.java) {

    private val splashScreenComponent: SplashScreenComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerComponentFactory.getSplashScreenComponent(activity as SupportActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.result.observe(this, Observer{

            /*if(it == SplashOperationResultEnum.USER_LOADED)
                onUserLoaded(viewModel.user.value!!)
            else
                onUserNotAvailable()*/

        })

    }

    override fun onStart() {
        super.onStart()
        viewModel.loadUser()
    }

    override fun layoutId(): Int = R.layout.fragment_splash_screen


    override fun onInject() {
        splashScreenComponent.inject(this)
    }

    private fun onUserLoaded(user: User) {
        //navigate(R.id.action_splashScreenFragment_to_homeFragment2)
    }

    private fun onUserNotAvailable() {
        //navigate(R.id.action_splashScreenFragment_to_landingScreenFragment)
    }

}
