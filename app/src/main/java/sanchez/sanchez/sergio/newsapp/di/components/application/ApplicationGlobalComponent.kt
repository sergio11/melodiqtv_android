package sanchez.sanchez.sergio.newsapp.di.components.application

import dagger.Component
import sanchez.sanchez.sergio.brownie.di.components.ApplicationComponent
import sanchez.sanchez.sergio.brownie.di.modules.ActivityModule
import sanchez.sanchez.sergio.brownie.di.scopes.PerApplication
import sanchez.sanchez.sergio.newsapp.di.components.activity.ApplicationActivityComponent
import sanchez.sanchez.sergio.healthycitizen.di.modules.UtilsModule

@PerApplication
@Component(dependencies = [ApplicationComponent::class],
    modules = [
        UtilsModule::class])
interface ApplicationGlobalComponent{

    fun activityComponent(activityModule: ActivityModule) : ApplicationActivityComponent

}