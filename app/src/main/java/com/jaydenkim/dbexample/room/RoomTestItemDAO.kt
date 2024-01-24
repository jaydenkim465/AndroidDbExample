package com.jaydenkim.dbexample.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jaydenkim.dbexample.sqlitehelper.SQLiteTestItemDAO
import java.text.SimpleDateFormat
import java.util.Date

@Dao
interface RoomTestItemDAO {
	@Query("SELECT * FROM TEST_ITEM_TB")
	fun getAll(): List<RoomTestItem>

	@Insert
	fun insert(data: RoomTestItem)

	fun insertItem(item: RoomTestItem) {
		insert(item.apply {
			val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			regDate = df.format(Date())
			updDate = df.format(Date())
		})
	}

	@Update
	fun update(item: RoomTestItem)

	@Delete
	fun delete(item: RoomTestItem)
}