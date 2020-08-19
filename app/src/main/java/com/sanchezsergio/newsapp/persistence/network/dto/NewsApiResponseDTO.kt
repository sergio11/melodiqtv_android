package com.sanchezsergio.newsapp.persistence.network.dto

data class NewsApiResponseDTO (
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleDTO>
)