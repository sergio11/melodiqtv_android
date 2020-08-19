package com.sanchezsergio.newsapp.persistence.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sanchezsergio.newsapp.persistence.db.entity.ArticleEntity

@Dao
interface NewsDao {
	
	@Query("SELECT * FROM articles")
	fun getAllItems():PagingSource<Int, ArticleEntity>
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAllItems(articleEntities:List<ArticleEntity>)
	
	@Query("DELETE FROM articles")
	suspend fun clearItems()
}