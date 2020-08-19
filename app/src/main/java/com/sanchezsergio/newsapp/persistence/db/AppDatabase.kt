package com.sanchezsergio.newsapp.persistence.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sanchezsergio.newsapp.persistence.db.dao.NewsDao
import com.sanchezsergio.newsapp.persistence.db.entity.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

	/**
	 * DAO
	 */
	abstract fun newsDao(): NewsDao
	
	companion object{
		@Volatile private var instance:AppDatabase? = null
		fun getDatabase(context: Context):AppDatabase =
			instance?: synchronized(this){
				instance?: buildDatabase(context).apply { instance = this }
			}
		
		private fun buildDatabase(context: Context) =
			Room.databaseBuilder(context,AppDatabase::class.java,"news.db")
				.fallbackToDestructiveMigration()
				.build()
	}
}