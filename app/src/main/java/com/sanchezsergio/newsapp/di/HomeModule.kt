package com.sanchezsergio.newsapp.di

import com.sanchezsergio.newsapp.view.activity.ui.home.HomePageAdapter
import com.sanchezsergio.newsapp.view.activity.ui.home.ItemComparator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object HomeModule {

	
	@Provides
	fun provideHomePageAdapter() =
		HomePageAdapter(ItemComparator)
}