package com.sanchezsergio.newsapp.persistence.network.source

import android.util.Log
import androidx.paging.PagingSource
import com.sanchezsergio.newsapp.domain.models.Article
import com.sanchezsergio.newsapp.persistence.network.mapper.ArticleMapper
import com.sanchezsergio.newsapp.persistence.network.service.NewsService
import kotlin.math.max

/**
 * Top Head lines page source
 */
class TopHeadlinesPageSource(
	private val newsService: NewsService): PagingSource<Int, Article>() {


	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
		try {

			val page = params.key ?: START_PAGE_KEY

			val response = newsService.getTopHeadlines(page)


			val result: List<Article> = response.body()?.run { ArticleMapper.dtoToModel(articles) } ?: emptyList()

			
			return LoadResult.Page(
				data = result,
				prevKey = max(page-1, START_PAGE_KEY),
				nextKey = page+1
			)
		}catch (e:Throwable){
			Log.e("Error","Item Page Source",e)
			return LoadResult.Error(e)
		}
	}

	companion object {

		private const val START_PAGE_KEY = 1

	}
}