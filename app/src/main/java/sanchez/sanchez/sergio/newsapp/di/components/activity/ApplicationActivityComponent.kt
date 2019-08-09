package sanchez.sanchez.sergio.newsapp.di.components.activity

import dagger.Subcomponent
import sanchez.sanchez.sergio.brownie.di.components.ActivityComponent
import sanchez.sanchez.sergio.brownie.di.modules.ActivityModule
import sanchez.sanchez.sergio.brownie.di.scopes.PerActivity
import sanchez.sanchez.sergio.newsapp.di.components.fragment.SplashScreenComponent
import sanchez.sanchez.sergio.newsapp.ui.features.MainActivity

@PerActivity
@Subcomponent(modules = [
    ActivityModule::class
    ])
interface ApplicationActivityComponent: ActivityComponent {

    fun inject(activity: MainActivity)

    fun splashScreenComponent(): SplashScreenComponent


}