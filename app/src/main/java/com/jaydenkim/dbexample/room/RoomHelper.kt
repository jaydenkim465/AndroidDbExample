package com.jaydenkim.dbexample.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
	entities = [RoomTestItem::class],
	version = 1
)

abstract class RoomHelper : RoomDatabase() {
	abstract fun getTestItemDAO(): RoomTestItemDAO

	companion object {
		private const val DB_NAME = "Room.db"

		// Singleton
		private var instance: RoomHelper? = null
		fun getInstance(context: Context): RoomHelper {
			return instance ?: buildDatabase(context).also {
				instance = it
			}
		}

		private fun buildDatabase(context: Context): RoomHelper =
			Room.databaseBuilder(
				context.applicationContext,
				RoomHelper::class.java,
				DB_NAME
			).build()
	}
}