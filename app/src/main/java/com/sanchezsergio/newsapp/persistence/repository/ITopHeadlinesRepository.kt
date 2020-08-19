package com.sanchezsergio.newsapp.persistence.repository

import androidx.paging.PagingData
import com.sanchezsergio.newsapp.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface ITopHeadlinesRepository {

    /**
     * Get Top Headlines
     */
    fun getTopHeadlines(): Flow<PagingData<Article>>
}