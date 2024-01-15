package com.jaydenkim.dbexample.sqlitehelper

class SQLiteTestItem {
	val _id get() = __id
	val itemId get() = _itemId
	val regDate get() = _regDate
	val updDate get() = _updDate

	var itemCd = ""
	var itemValue = ""

	private var __id = ""
	private var _itemId = ""
	private var _regDate = ""
	private var _updDate = ""

	constructor(itemId: String, itemCd: String, itemValue: String) {
		_itemId = itemId
		this.itemCd = itemCd
		this.itemValue = itemValue
	}

	constructor(_id: String, _itemId: String, itemCd:String, itemValue:String, _regDate:String, _updDate:String) {
		__id = _id
		this._itemId = _itemId
		this.itemCd = itemCd
		this.itemValue = itemValue
		this._regDate = _regDate
		this._updDate = _updDate
	}
}