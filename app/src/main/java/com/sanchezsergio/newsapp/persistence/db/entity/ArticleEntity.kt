package com.sanchezsergio.newsapp.persistence.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(
		@PrimaryKey
		val id: Int,
		val author: String,
		val title: String,
		val description: String,
		val url: String,
		val urlToImage: String,
		val publishedAt: String,
		val content: String,
		val created: String,
		val gender: String,
		@Embedded val source: ArticleSourceEntity
)

data class ArticleSourceEntity(
	@ColumnInfo(name = "source_id") val id: String,
	@ColumnInfo(name = "source_name")val name: String
)