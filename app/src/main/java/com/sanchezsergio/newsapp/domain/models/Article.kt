package com.sanchezsergio.newsapp.domain.models

data class Article(
        val author: String,
        val title: String,
        val description: String,
        val url: String,
        val urlToImage: String,
        val publishedAt: String,
        val content: String,
        val source: ArticleSource
)