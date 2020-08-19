package com.sanchezsergio.newsapp.persistence.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.sanchezsergio.newsapp.persistence.network.source.TopHeadlinesPageSource
import javax.inject.Inject

class TopHeadlinesITopHeadlinesRepository @Inject constructor(
	private val topLinesPageSource: TopHeadlinesPageSource): ITopHeadlinesRepository {
	
	override fun getTopHeadlines() = Pager(config =  PagingConfig(5)){
		topLinesPageSource
	}.flow
}
