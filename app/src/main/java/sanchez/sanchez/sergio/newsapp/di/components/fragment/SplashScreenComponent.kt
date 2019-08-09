package sanchez.sanchez.sergio.newsapp.di.components.fragment

import dagger.Subcomponent
import sanchez.sanchez.sergio.brownie.di.components.FragmentComponent
import sanchez.sanchez.sergio.brownie.di.scopes.PerFragment
import sanchez.sanchez.sergio.newsapp.di.modules.viewmodel.SplashViewModelModule
import sanchez.sanchez.sergio.newsapp.ui.features.splash.SplashScreenFragment

@PerFragment
@Subcomponent(
    modules = [
        SplashViewModelModule::class ])
interface SplashScreenComponent: FragmentComponent {

    fun inject(splashScreenFragment: SplashScreenFragment)
}