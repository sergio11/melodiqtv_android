package sanchez.sanchez.sergio.newsapp.ui.features

import android.os.Bundle
import sanchez.sanchez.sergio.brownie.ui.core.activity.SupportActivity
import sanchez.sanchez.sergio.healthycitizen.R
import sanchez.sanchez.sergio.newsapp.di.components.activity.ApplicationActivityComponent
import sanchez.sanchez.sergio.newsapp.di.factory.DaggerComponentFactory

class MainActivity : SupportActivity() {

    private val activityComponent: ApplicationActivityComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerComponentFactory.getAppActivityComponent(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_NoActionBar)
        super.onCreate(savedInstanceState)
    }

    override fun layoutId(): Int = R.layout.activity_main

    override fun navHostId(): Int = R.id.navHostContainer


    override fun onSinglePermissionGranted(permission: String) {
    }

    override fun onSinglePermissionRejected(permission: String) {}

    override fun onErrorOccurred(permission: String) {}

    override fun onInject() {
        activityComponent.inject(this)
    }
}
