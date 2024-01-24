package com.jaydenkim.dbexample.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "TEST_ITEM_TB")
data class RoomTestItem(
	@PrimaryKey(autoGenerate = true) val _id: Int,
	@ColumnInfo(name = "ITEM_ID") val itemId: String?,
	@ColumnInfo(name = "ITEM_CD") val itemCd: String?,
	@ColumnInfo(name = "ITEM_VALUE") val itemValue: String?,
	@ColumnInfo(name = "REG_DATE") var regDate: String?,
	@ColumnInfo(name = "UPD_DATE") var updDate: String?
) {
	@Ignore
	constructor(itemId: String, itemCd: String, itemValue: String) : this(
		0,
		itemId,
		itemCd,
		itemValue,
		"",
		""
	)
}
