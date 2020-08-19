package com.sanchezsergio.newsapp.di.persistence.network

import android.content.Context
import com.sanchezsergio.newsapp.persistence.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context) =
            AppDatabase.getDatabase(context)
}