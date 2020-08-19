package com.sanchezsergio.newsapp.persistence.network.service

import com.sanchezsergio.newsapp.persistence.network.dto.NewsApiResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

	/**
	 *
	 */
	@GET("top-headlines/")
	suspend fun getTopHeadlines(@Query("page") page:Int) : Response<NewsApiResponseDTO>
}