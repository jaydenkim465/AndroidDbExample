package com.jaydenkim.dbexample.realm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class RealmTestItem() : RealmObject {
	@PrimaryKey
	var _id: ObjectId = ObjectId()
	var itemId = ""
	var regDate = ""
	var updDate = ""

	var itemCd = ""
	var itemValue = ""

	constructor(itemId: String, itemCd: String, itemValue: String) : this() {
		this.itemId = itemId
		this.itemCd = itemCd
		this.itemValue = itemValue
	}
}