package com.jaydenkim.dbexample.realm

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.max
import java.text.SimpleDateFormat
import java.util.Date

class RealmTestItemDAO {
	val config: RealmConfiguration

	init {
		config = RealmConfiguration.create(schema = setOf(RealmTestItem::class))
	}

	fun getAll(): List<RealmTestItem> {
		val realm = Realm.open(config)
		val result = realm.query<RealmTestItem>().find()
		val list = mutableListOf<RealmTestItem>()

		if (result.size > 0) {
			for (i in 0..result.size - 1) {
				list.add(result[i])
			}
		}
		realm.close()

		return list
	}

	suspend fun insert(item: RealmTestItem) {
		val realm = Realm.open(config)
		realm.write {
			val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			item.regDate = df.format(Date())
			item.updDate = df.format(Date())
			copyToRealm(item)
		}
		realm.close()
	}

	suspend fun update(item: RealmTestItem) {
		val realm = Realm.open(config)
		realm.write {
			val result = realm.query<RealmTestItem>("itemId == '${item.itemId}'").find().first()
			if(result != null) {
				val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				result.itemCd = item.itemCd
				result.itemValue = item.itemValue
				result.updDate = df.format(Date())
			}
		}
		realm.close()
	}

	suspend fun delete(item: RealmTestItem) {
		val realm = Realm.open(config)
		val result = realm.query<RealmTestItem>("itemId == '${item.itemId}'").find().first()
		realm.write {
			if(result != null) {
				findLatest(result).also {
					delete(it!!)
				}
			}
		}
		realm.close()
	}
}