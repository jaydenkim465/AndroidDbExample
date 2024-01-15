package com.jaydenkim.dbexample.sqlitehelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper private constructor(context: Context) :
	SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
	companion object {
		private const val DB_NAME = "OnlySQLite.db"
		private const val DB_VERSION = 1

		// Singleton
		private var instance: SQLiteHelper? = null
		fun getInstance(_context: Context): SQLiteHelper {
			return instance ?: SQLiteHelper(_context).also {
				instance = it
			}
		}
	}

	override fun onCreate(db: SQLiteDatabase) {
		db.execSQL(SQLiteTestItemDAO.SQL_CREATE_TABLE)
	}

	override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
	}

	override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
	}

	fun select(
		tableName: String,
		projection: Array<String>? = null,
		selection: String? = null,
		selectionArg: Array<String>? = null,
		groupBy: String? = null,
		groupByArg: String? = null,
		sortOrder: String? = null
	): Cursor {
		val db = readableDatabase
		return db.query(
			tableName,
			projection,
			selection,
			selectionArg,
			groupBy,
			groupByArg,
			sortOrder
		)
	}

	fun insert(tableName: String, values: ContentValues): Boolean {
		val db = readableDatabase
		return db.insert(tableName, null, values) != -1L
	}

	fun update(
		tableName: String,
		values: ContentValues,
		whereOption: String?,
		whereArg: Array<String>?
	) {
		val db = readableDatabase
		db.update(tableName, values, whereOption, whereArg)
	}

	fun delete(tableName: String, whereOption: String?, whereArg: Array<String>?) {
		val db = readableDatabase
		db.delete(tableName, whereOption, whereArg)
	}
}