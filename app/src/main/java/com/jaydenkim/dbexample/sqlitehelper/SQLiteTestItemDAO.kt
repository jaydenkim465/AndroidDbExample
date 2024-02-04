package com.jaydenkim.dbexample.sqlitehelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import java.text.SimpleDateFormat
import java.util.Date

class SQLiteTestItemDAO {
	object ItemColumn {
		const val TABLE_NAME = "TEST_ITEM_TB"

		const val _ID = "_id"
		const val ITEM_ID = "ITEM_ID"
		const val ITEM_CD = "ITEM_CD"
		const val ITEM_VALUE = "ITEM_VALUE"
		const val REG_DATE = "REG_DATE"
		const val UPD_DATE = "UPD_DATE"
	}

	companion object {
		const val SQL_CREATE_TABLE =
			"CREATE TABLE ${ItemColumn.TABLE_NAME} (" +
					"${ItemColumn._ID} INTEGER PRIMARY KEY, " +
					"${ItemColumn.ITEM_ID} TEXT UNIQUE NOT NULL, " +
					"${ItemColumn.ITEM_CD} TEXT, " +
					"${ItemColumn.ITEM_VALUE} TEXT, " +
					"${ItemColumn.REG_DATE} TEXT NOT NULL, " +
					"${ItemColumn.UPD_DATE} TEXT )"

		const val SQL_DROP_TABLE =
			"DROP TABLE IF EXIST ${ItemColumn.TABLE_NAME}"
	}

	fun selectAll(context: Context): Array<SQLiteTestItem> {
		val db = SQLiteHelper.getInstance(context)
		val cursor = db.select(ItemColumn.TABLE_NAME)
		return cursorToTestItem(cursor)
	}

	fun selectKeyword(context: Context, type: String, keyword: String): Array<SQLiteTestItem> {
		val db = SQLiteHelper.getInstance(context)
		val cursor = db.select(ItemColumn.TABLE_NAME, selection = type, selectionArg = arrayOf(keyword))
		return cursorToTestItem(cursor)
	}

	fun insertItem(context: Context, item: SQLiteTestItem) {
		val db = SQLiteHelper.getInstance(context)
		val values = ContentValues().apply {
			val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			put(ItemColumn.ITEM_ID, item.itemId)
			put(ItemColumn.ITEM_CD, item.itemCd)
			put(ItemColumn.ITEM_VALUE, item.itemValue)
			put(ItemColumn.REG_DATE, df.format(Date()))
			put(ItemColumn.UPD_DATE, df.format(Date()))
		}
		db.insert(ItemColumn.TABLE_NAME, values)
	}

	fun updateItem(context: Context, item: SQLiteTestItem) {
		val db = SQLiteHelper.getInstance(context)
		val values = ContentValues().apply {
			val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			put(ItemColumn.ITEM_CD, item.itemCd)
			put(ItemColumn.ITEM_VALUE, item.itemValue)
			put(ItemColumn.UPD_DATE, df.format(Date()))
		}
		val whereString = "${ItemColumn.ITEM_ID} = ?"
		db.update(ItemColumn.TABLE_NAME, values, whereString, arrayOf(item.itemId))
	}

	fun deleteItem(context: Context, item: SQLiteTestItem) {
		val db = SQLiteHelper.getInstance(context)
		val whereString = "${ItemColumn.ITEM_ID} = ?"
		db.delete(ItemColumn.TABLE_NAME, whereString, arrayOf(item.itemId))
	}

	private fun cursorToTestItem(cursor: Cursor): Array<SQLiteTestItem> {
		val list = mutableListOf<SQLiteTestItem>()
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				do {
					val dbId = cursor.getString(0)
					val itemId = cursor.getString(1)
					val itemCd = cursor.getString(2)
					val itemValue = cursor.getString(3)
					val regDate = cursor.getString(4)
					val udpDate = cursor.getString(5)

					val item = SQLiteTestItem(dbId, itemId, itemCd, itemValue, regDate, udpDate)
					list.add(item)
				} while (cursor.moveToNext())
			}
			cursor.close()
		}
		return list.toTypedArray()
	}
}