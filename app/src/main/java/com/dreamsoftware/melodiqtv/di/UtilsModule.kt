package com.dreamsoftware.melodiqtv.di

import android.content.Context
import com.dreamsoftware.melodiqtv.utils.network.NetworkConnectivityCallback
import com.dreamsoftware.melodiqtv.utils.network.NetworkConnectivityMonitor
import com.dreamsoftware.fudge.utils.FudgeTvEventBus
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UtilsModule {

    @Singleton
    @Provides
    fun provideAppEventBus() = FudgeTvEventBus()

    @Singleton
    @Provides
    fun provideNetworkCallback(appEventBus: FudgeTvEventBus) = NetworkConnectivityCallback(appEventBus)

    @Singleton
    @Provides
    fun provideNetworkConnectivityMonitor(
        @ApplicationContext context: Context,
        networkConnectivityCallback: NetworkConnectivityCallback
    ) = NetworkConnectivityMonitor(context, networkConnectivityCallback)
}