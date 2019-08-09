package sanchez.sanchez.sergio.newsapp.domain.model

import java.util.*

/**
 * Article From Source
 */
data class Article(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: Date,
    val content: String
)