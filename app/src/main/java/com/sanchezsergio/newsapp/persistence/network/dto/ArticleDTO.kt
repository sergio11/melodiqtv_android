package com.sanchezsergio.newsapp.persistence.network.dto

data class ArticleDTO(
        val author: String,
        val title: String,
        val description: String,
        val url: String,
        val urlToImage: String,
        val publishedAt: String,
        val content: String,
        val source: ArticleSourceDTO
)