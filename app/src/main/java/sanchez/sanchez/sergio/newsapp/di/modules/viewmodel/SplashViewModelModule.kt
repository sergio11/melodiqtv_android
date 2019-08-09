package sanchez.sanchez.sergio.newsapp.di.modules.viewmodel

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import sanchez.sanchez.sergio.brownie.di.modules.ViewModelModule
import sanchez.sanchez.sergio.brownie.di.scopes.PerFragment
import sanchez.sanchez.sergio.brownie.di.viewmodel.ViewModelKey
import sanchez.sanchez.sergio.newsapp.ui.features.splash.SplashViewModel

@Module(includes = [ ViewModelModule::class ])
abstract class SplashViewModelModule {

    @PerFragment
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindsSplashViewModel(splashViewModel: SplashViewModel): ViewModel

}