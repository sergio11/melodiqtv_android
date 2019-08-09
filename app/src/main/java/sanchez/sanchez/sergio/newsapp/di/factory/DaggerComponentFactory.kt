package sanchez.sanchez.sergio.newsapp.di.factory

import sanchez.sanchez.sergio.brownie.BrownieApp
import sanchez.sanchez.sergio.brownie.di.components.ApplicationComponent
import sanchez.sanchez.sergio.brownie.di.components.DaggerApplicationComponent
import sanchez.sanchez.sergio.brownie.di.modules.ActivityModule
import sanchez.sanchez.sergio.brownie.di.modules.ApplicationModule
import sanchez.sanchez.sergio.brownie.ui.core.activity.SupportActivity
import sanchez.sanchez.sergio.newsapp.di.components.activity.ApplicationActivityComponent
import sanchez.sanchez.sergio.newsapp.di.components.application.ApplicationGlobalComponent
import sanchez.sanchez.sergio.newsapp.di.components.application.DaggerApplicationGlobalComponent
import sanchez.sanchez.sergio.newsapp.di.components.fragment.SplashScreenComponent


object DaggerComponentFactory {

    private var appComponent: ApplicationComponent? = null
    private var appGlobalComponent: ApplicationGlobalComponent? = null
    var appActivityComponent: ApplicationActivityComponent? = null

    fun getAppComponent(app: BrownieApp): ApplicationComponent =
        appComponent ?: DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(app))
            .build().also {
                appComponent = it
            }

    fun getAppGlobalComponent(app: BrownieApp): ApplicationGlobalComponent =
        appGlobalComponent ?: DaggerApplicationGlobalComponent.builder()
            .applicationComponent(getAppComponent(app))
            .build().also {
                appGlobalComponent = it
            }

    fun getAppActivityComponent(activity: SupportActivity): ApplicationActivityComponent =
        appActivityComponent ?: getAppGlobalComponent(activity.application as BrownieApp)
            .activityComponent(ActivityModule(activity)).also {
                appActivityComponent = it
            }

    fun getSplashScreenComponent(activity: SupportActivity): SplashScreenComponent =
        getAppActivityComponent(activity).splashScreenComponent()

}